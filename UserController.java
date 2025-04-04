package com.zpy.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpy.pojo.*;
import com.zpy.service.*;
import com.zpy.service.recommend.MyUserBasedRecommenderImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class UserController {
    @Value("${location}")
    private String location;
    @Autowired
    private CommentService commentService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ScoreScenicService scoreScenicService;
    @Autowired
    private ScenicService scenicService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private HotelService  hotelService;
    @Autowired
    private SorderService sorderService;
    @Autowired
    private HorderService horderService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private RoomService roomService;

    @RequestMapping("userlogin")
    public String userlogin(String username, String password, Model model, HttpSession session){
        boolean b=customerService.login(username,password);
        if (b){
            QueryWrapper<Customer>qw=new QueryWrapper<>();
            qw.eq("customer_name",username);
            Customer user = customerService.getOne(qw);
            session.setAttribute("currentUser",username);
            session.setAttribute("userId",user.getId());
            session.setAttribute("image",user.getCimage());
            session.setAttribute("email",user.getEmail());
            session.setAttribute("phone",user.getPhone());
            session.setAttribute("password",password);


            List<Scenic> list = scenicService.list(null);
            model.addAttribute("scenicList",list);
            List<Hotel> list1 = hotelService.list(null);
            model.addAttribute("hotelList",list1);


            List<Customer> list2 = customerService.list(null);
            model.addAttribute("peopleCount",list2.size());
            model.addAttribute("scenicCount",list.size());

            QueryWrapper<SOrder>qw1=new QueryWrapper<>();
            qw1.notLike("status",3);
            List<SOrder> list3 = sorderService.list(qw1);
            model.addAttribute("sorderCount",list3.size());

            QueryWrapper<Comment>queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("star",5);
            List<Comment> list4 = commentService.list(queryWrapper);
            model.addAttribute("starCount",list4.size());

            Set<String>set=new HashSet<>();
            for (Scenic scenic : list) {
                set.add(scenic.getCountry());
            }
            model.addAttribute("countryList",set);

            //推荐部分
            if(session.getAttribute("userId")!=null)
            {
                Integer userId= (Integer) session.getAttribute("userId");//获取当前用户的id
                String currentUser= (String) session.getAttribute("currentUser");//获取当前用户的姓名
                log.info(currentUser);
                MyUserBasedRecommenderImpl muser=new MyUserBasedRecommenderImpl();//new 一个业务层实现类来调取其中的一些关键方法
                List<RecommendedItem> listU=muser.userBasedRecommender(userId,4);//调用userBasedRecommender方法，5代表最终推荐的图书的数量
                log.info(listU.toString());
                if(listU.equals(null)){//对返回的list集合进行遍历，目的是封装图书的list集合
                    List<Scenic> listByU = null;//判断集合是否为空
                    //List<BookNew> listByI = null;

                    model.addAttribute("recommendList",listByU);//将查到的推荐的图书集合的数据库保存在model中
                    //model.addAttribute("listByI",listByI);
                }
                else {
                    List<Scenic> listByU = getRecommend(listU);//调用getRecommend方法封装关于推荐图书的list集合
                    //List<BookNew> listByI = getRecommend(listI);
                    model.addAttribute("recommendList",listByU);//将查到的推荐的图书集合的数据库保存在model中
                    //model.addAttribute("listByI",listByI);
                }

            }

            return "user-home";
        }else {
            model.addAttribute("msg","用户名或密码错误！");
            return "user-login";
        }
    }
    public List<Scenic> getRecommend(List<RecommendedItem> list){//此方法的作用是封装关于推荐图书的list集合
        List<Scenic> listBook = new ArrayList<>();//new 一个关于图书的list集合
        for(RecommendedItem r:list){//遍历RecommendedItem的集合
            Integer id= Math.toIntExact(r.getItemID());//获取图书id
            Scenic book=scenicService.getById(id);//调用mybatis-plus的getById方法根据id获取图书对象
            listBook.add(book);//将图书封装到图书集合中
        }
        return listBook;//返回推荐的图书的集合
    }

    @RequestMapping("listScenic")
    public String listScenic(@RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "6",required = false)Integer pageSize, Model model,Scenic scenic){
        if (pageNum<0||pageNum.equals("")||pageNum==null){
            pageNum=1;
        }
        if (pageSize<0||pageSize.equals("")||pageSize==null){
            pageSize=6;
        }
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Scenic>qw=new QueryWrapper<>();
        if (scenic.getSname()!=null){
            qw.like("sname",scenic.getSname());
        }
        List<Scenic> list = scenicService.list(qw);
        PageInfo<Scenic>pageInfo=new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "user-scenic-list";
    }

    @RequestMapping("scenicSingle/{id}")
    public String scenicSingle(@PathVariable Integer id,Model model,HttpSession session){
        session.setAttribute("scenicId",id);
        Scenic byId = scenicService.getById(id);
        model.addAttribute("scenic",byId);

        List<String>list=new ArrayList<>();
        list.add(byId.getSimage());
        QueryWrapper<Route>qw=new QueryWrapper<>();
        qw.eq("sid",id);
        List<Route> list1 = routeService.list(qw);
        if (list1!=null){
            for (Route route : list1) {
                list.add(route.getRimage());
            }
        }

        model.addAttribute("routeList",list1);
        model.addAttribute("imgList",list);

        QueryWrapper<Comment>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("scenic_id",id);
        List<Comment> list2 = commentService.list(queryWrapper);
        model.addAttribute("commentList",list2);
        return "user-scenic-single";
    }

    @RequestMapping("addComment")
    public String addComment(Comment comment,HttpSession session){

        String currentUser = (String) session.getAttribute("currentUser");
        String image = (String) session.getAttribute("image");
        Integer userId = (Integer) session.getAttribute("userId");
        comment.setCustomer(currentUser);
        comment.setCimage(image);
        comment.setCommentTime(new Date());
        boolean save = commentService.save(comment);

        ScoreScenic scoreScenic = new ScoreScenic();
        scoreScenic.setUserId(userId);
        scoreScenic.setScenicId(comment.getScenicId());
        scoreScenic.setScore(comment.getStar());
        scoreScenic.setTime(new Date());
        scoreScenicService.save(scoreScenic);

        return "redirect:/scenicSingle/"+comment.getScenicId();
    }

    @RequestMapping("addOrder")
    public String addOrder(SOrder sOrder,String start,String end,HttpSession session) throws ParseException {
        Integer userId = (Integer) session.getAttribute("userId");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        Date startTime = simpleDateFormat.parse(start);
        Date endTime = simpleDateFormat.parse(end);
        Scenic scenic = scenicService.getById(sOrder.getSid());
        double total = scenic.getPrice() * sOrder.getCount();

        sOrder.setCid(userId);
        sOrder.setStartTime(startTime);
        sOrder.setEndTime(endTime);
        sOrder.setTotal(total);
        sOrder.setStatus(0);
        boolean save = sorderService.save(sOrder);
        return "redirect:/listMySorder";
    }

    @RequestMapping("listHotel")
    public String listHotel(@RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "6",required = false)Integer pageSize, Model model, Hotel hotel){
        if (pageNum<0||pageNum.equals("")||pageNum==null){
            pageNum=1;
        }
        if (pageSize<0||pageSize.equals("")||pageSize==null){
            pageSize=6;
        }
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Hotel>queryWrapper=new QueryWrapper<>();
        if (hotel.getSid()!=null){
            queryWrapper.eq("sid",hotel.getSid());
        }
        List<Hotel> list = hotelService.list(queryWrapper);
        for (Hotel hotel1 : list) {
            hotel1.setSname(scenicService.getById(hotel1.getSid()).getSname());
        }
        PageInfo<Hotel>pageInfo=new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        List<Scenic> list1 = scenicService.list(null);
        model.addAttribute("scenicList",list1);
        return "user-hotel-list";
    }

    @RequestMapping("hotelSingle/{id}")
    public String hotelSingle(@PathVariable Integer id,Model model,HttpSession session){
        session.setAttribute("hotelId",id);
        Hotel hotel = hotelService.getById(id);
        hotel.setSname(scenicService.getById(hotel.getSid()).getSname());
        model.addAttribute("hotel",hotel);
        List<String>list=new ArrayList<>();
        list.add(hotel.getHimage());

        QueryWrapper<Room>qw=new QueryWrapper<>();
        qw.eq("hid",id);
        List<Room> list1 = roomService.list(qw);
        if (list1!=null){
            for (Room room : list1) {
                list.add(room.getRimage());
            }
        }

        model.addAttribute("roomList",list1);
        model.addAttribute("imgList",list);
        return "user-hotel-single";
    }


    @RequestMapping("addHorder")
    public String addHorder(Horder horder,String start,String end,Model model,HttpSession session) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        Date startTime = simpleDateFormat.parse(start);
        Date endTime = simpleDateFormat.parse(end);
        Integer userId = (Integer) session.getAttribute("userId");
        int i = endTime.getDay() - startTime.getDay();
        horder.setCid(userId);
        horder.setStartTime(startTime);
        horder.setEndTime(endTime);
        horder.setCount(i);
        horder.setTotal(i*roomService.getById(horder.getRid()).getPrice());
        horder.setStatus(0);
        boolean save = horderService.save(horder);
        return "redirect:/listMyHorder";
    }

    @RequestMapping("listWish")
    public String listWish(HttpSession session,Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        List<SOrder>list=sorderService.listWish(userId);
        model.addAttribute("wishList",list);
        return "user-wish-list";

    }

    @RequestMapping("addWish/{id}")
    public String addWish(@PathVariable Integer id,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        QueryWrapper<SOrder>qw=new QueryWrapper<>();
        qw.eq("cid",userId);
        qw.eq("sid",id);
        qw.eq("status",3);
        SOrder one = sorderService.getOne(qw);
        if (one!=null){
            return "redirect:/listWish";
        }
        SOrder sOrder = new SOrder();
        sOrder.setCid(userId);
        sOrder.setSid(id);
        sOrder.setStatus(3);
        sorderService.save(sOrder);
        return "redirect:/listWish";
    }

    @RequestMapping("delWish/{id}")
    public String delWish(@PathVariable Integer id){
        boolean b = sorderService.removeById(id);
        return "redirect:/listWish";
    }

    @RequestMapping("listMySorder")
    public String listMySorder(HttpSession session,Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        List<SOrder>list=sorderService.listMySorder(userId);
        PageInfo<SOrder>pageInfo=new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "user-order-scenic";
    }

    @RequestMapping("delMyOrder/{id}")
    public String delMyOrder(@PathVariable Integer id){
        boolean b = sorderService.removeById(id);
        return "redirect:/listMySorder";
    }

    @RequestMapping("listMyHorder")
    public String listMyHorder(HttpSession session,Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        QueryWrapper<Horder>qw=new QueryWrapper<>();
        qw.eq("cid",userId);
        List<Horder> list = horderService.list(qw);
        for (Horder horder : list) {
            horder.setHimage(hotelService.getById(roomService.getById(horder.getRid()).getHid()).getHimage());
            horder.setHname(hotelService.getById(roomService.getById(horder.getRid()).getHid()).getHname());
            horder.setCity(hotelService.getById(roomService.getById(horder.getRid()).getHid()).getCity());
            horder.setAddress(hotelService.getById(roomService.getById(horder.getRid()).getHid()).getAddress());
            horder.setRname(roomService.getById(horder.getRid()).getRname());
            horder.setPrice(roomService.getById(horder.getRid()).getPrice());
        }
        PageInfo<Horder>pageInfo=new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "user-order-hotel";
    }



    @RequestMapping("delMyHorder/{id}")
    public String delMyHorder(@PathVariable Integer id){
        boolean b = horderService.removeById(id);
        return "redirect:/listMyHorder";
    }

    @RequestMapping("listNotice")
    public String listNotice(Model model){
        List<Notice> list = noticeService.list(null);
        model.addAttribute("noticeList",list);
        return "user-notice-list";
    }

    @RequestMapping("userProfile")
    public String profile(HttpSession session,Model model ){
        session.removeAttribute("loginFail");
        String currentUser = (String) session.getAttribute("currentUser");
        String  password = (String) session.getAttribute("password");
        QueryWrapper<Customer>qw=new QueryWrapper<>();
        qw.eq("customer_name",currentUser);
        Customer one = customerService.getOne(qw);
        one.setPassword(password);
        model.addAttribute("user",one);
        return "user-profile-info";

    }

    //查询所有订单
    @RequestMapping("/userProfileSorder")
    public String userProfileSorder(@RequestParam(required = false,value = "pageNum",defaultValue = "1")Integer pageNum,
                                    @RequestParam(required = false,value = "pageSize",defaultValue = "10")Integer pageSize, Model model,HttpSession session){
        if (pageNum<=0||pageNum.equals("")||pageNum==null){
            pageNum=1;
        }
        if (pageSize<=0||pageSize.equals("")||pageSize==null){
            pageSize=10;
        }
        Integer userId = (Integer) session.getAttribute("userId");


        PageHelper.startPage(pageNum,pageSize);
        List<SOrder> list = sorderService.listMySorder(userId);
        PageInfo<SOrder>pageInfo=new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "user-profile-sorder";
    }

    @RequestMapping("pay/{id}")
    public String pay(@PathVariable Integer id){
        SOrder byId = sorderService.getById(id);
        byId.setStatus(1);
        sorderService.updateById(byId);
        return "redirect:/userProfileSorder";
    }
    @RequestMapping("cancel/{id}")
    public String cancel(@PathVariable Integer id){
        SOrder byId = sorderService.getById(id);
        byId.setStatus(2);
        sorderService.updateById(byId);
        return "redirect:/userProfileSorder";
    }

    @RequestMapping("/userProfileHorder")
    public String userProfileHorder(@RequestParam(required = false,value = "pageNum",defaultValue = "1")Integer pageNum,
                                    @RequestParam(required = false,value = "pageSize",defaultValue = "10")Integer pageSize, Model model,HttpSession session){
        if (pageNum<=0||pageNum.equals("")||pageNum==null){
            pageNum=1;
        }
        if (pageSize<=0||pageSize.equals("")||pageSize==null){
            pageSize=10;
        }
        Integer userId = (Integer) session.getAttribute("userId");


        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Horder>qw=new QueryWrapper<>();
        qw.eq("cid",userId);
        List<Horder> horderList = horderService.list(qw);
        for (Horder horder : horderList) {
            horder.setHimage(hotelService.getById(roomService.getById(horder.getRid()).getHid()).getHimage());
            horder.setHname(hotelService.getById(roomService.getById(horder.getRid()).getHid()).getHname());
            horder.setCity(hotelService.getById(roomService.getById(horder.getRid()).getHid()).getCity());
            horder.setAddress(hotelService.getById(roomService.getById(horder.getRid()).getHid()).getAddress());
            horder.setRname(roomService.getById(horder.getRid()).getRname());
            horder.setPrice(roomService.getById(horder.getRid()).getPrice());
            horder.setRimage(roomService.getById(horder.getRid()).getRimage());
            horder.setBed(roomService.getById(horder.getRid()).getBed());
            horder.setBig(roomService.getById(horder.getRid()).getBig());
        }
        PageInfo<Horder> list = new PageInfo<>(horderList);
        model.addAttribute("pageInfo",list);
        log.info(list.toString());
        return "user-profile-Horder";
    }

    @RequestMapping("payHorder/{id}")
    public String payHorder(@PathVariable Integer id){
        Horder byId = horderService.getById(id);
        byId.setStatus(1);
        horderService.updateById(byId);
        return "redirect:/userProfileHorder";
    }
    @RequestMapping("cancelHorder/{id}")
    public String cancelHorder(@PathVariable Integer id){
        Horder byId = horderService.getById(id);
        byId.setStatus(2);
        horderService.updateById(byId);
        return "redirect:/userProfileHorder";
    }

    @RequestMapping("userProfileWish")
    public String userProfileWish(Model model,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");

        List<SOrder> list1 = sorderService.listWish(userId);
        model.addAttribute("wishList",list1);
        return "user-profile-wish";
    }

    @RequestMapping("userSetting")
    public String userSetting(HttpSession session,Model model ){

        String currentUser = (String) session.getAttribute("currentUser");
        String  password = (String) session.getAttribute("password");
        QueryWrapper<Customer>qw=new QueryWrapper<>();
        qw.eq("customer_name",currentUser);
        Customer one = customerService.getOne(qw);
        one.setPassword(password);
        model.addAttribute("user",one);
        return "user-profile-set";

    }


    @RequestMapping("userUpdateProfile")
    public String userUpdateProfile(Customer customer, MultipartFile file){
        if (!file.isEmpty()){
            transFilea(customer,file);//给customer对象传头像
        }
        String s = DigestUtil.md5Hex(customer.getPassword());
        customer.setPassword(s);
        boolean b = customerService.updateById(customer);
        return "redirect:/userSetting";
    }

    private void transFilea(Customer customer, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();//获得文件全名
        int index = originalFilename.lastIndexOf(".");//将文件名前后分开
        String suffix = originalFilename.substring(index);//构造后缀
        String prefix =System.nanoTime()+"";//构造前缀
        String path=prefix+suffix;//拼接成文件名
        File file1 = new File(location);//new 一个要下载的路径的file对象
        if (!file1.exists()){//如果电脑上不存在这个目录
            file1.mkdirs();//创建这个目录
        }
        File file2 = new File(file1,path);
        try {
            file.transferTo(file2);//调用上传方法
        } catch (IOException e) {
            e.printStackTrace();
        }
        customer.setCimage(path);//赋cimage字段
    }

    //修改密码
    @RequestMapping("userUpdatePassword")
    public String userUpdatePassword(String userPwd, String newPwd,String confirmPwd, Model model, HttpSession session) {
        if (!newPwd.equals(confirmPwd)){
            session.setAttribute("loginFail", "两次输入的的新密码不一致");
            return "redirect:/userSetting";
        }
        String currentUser = (String) session.getAttribute("currentUser");
        boolean login = customerService.login(currentUser, userPwd);
        if (login) {
            Customer user = new Customer();
            user.setCustomerName(currentUser);
            String newPassword = DigestUtil.md5Hex(newPwd);
            user.setPassword(newPassword);
            QueryWrapper<Customer>qw=new QueryWrapper<>();
            qw.eq("customer_name",user.getCustomerName());
            boolean b = customerService.update(user,qw);
            if (b) {
                return "redirect:/userSetting";
            } else {
                session.setAttribute("loginFail", "修改密码失败");
            }
        } else {
            session.setAttribute("loginFail", "用户验证失败");
        }
        return "redirect:/userSetting";
    }

    @RequestMapping("userlogout")
    public String userlogout(){
        return "user-login";
    }


    @RequestMapping("toUserLogin")
    public String toUserLogin(){
        return "user-login";
    }
    @RequestMapping("toUserRegister")
    public String toUserRegister(){
        return "user-register";
    }
    //注册
    @RequestMapping("/userRegister")
    public String register(String userName, String userPwd,String confirmPwd, Model model) {

        QueryWrapper<Customer>qw=new QueryWrapper<>();
        qw.eq("customer_name",userName);
        Customer one = customerService.getOne(qw);
        if (one!=null){
            model.addAttribute("msg", "该用户已存在");
            return "user-register";
        }

        if (!userPwd.equals(confirmPwd)){
            model.addAttribute("msg", "输入密码不一致");
            return "user-register";
        }else {
            Customer customer = new Customer();
            customer.setCustomerName(userName);
            String s = DigestUtil.md5Hex(userPwd);
            customer.setPassword(s);
            customerService.save(customer);
            return "user-login";
        }

    }

    @RequestMapping("user")
    public String user(Model model,HttpSession session){
        List<Scenic> list = scenicService.list(null);
        List<Hotel> list1 = hotelService.list(null);
        Set<String>set=new HashSet<>();
        for (Scenic scenic : list) {
            set.add(scenic.getCountry());
        }
        model.addAttribute("countryList",set);
        model.addAttribute("scenicList",list);
        model.addAttribute("hotelList",list1);


        QueryWrapper<SOrder>qw1=new QueryWrapper<>();
        qw1.notLike("status","3");
        List<SOrder> sOrderList = sorderService.list(qw1);
        log.info(sOrderList.size()+"");
        model.addAttribute("sorderCount",sOrderList.size());
        model.addAttribute("scenicCount",list.size());
        List<Customer> list2 = customerService.list(null);
        model.addAttribute("peopleCount",list2.size());
        QueryWrapper<Comment>qw2=new QueryWrapper<>();
        qw2.eq("star",5);
        List<Comment> list3 = commentService.list(qw2);
        model.addAttribute("starCount",list3.size());


        //推荐部分
        if(session.getAttribute("userId")!=null)
        {
            Integer userId= (Integer) session.getAttribute("userId");//获取当前用户的id
            String currentUser= (String) session.getAttribute("currentUser");//获取当前用户的姓名
            log.info(currentUser);
            MyUserBasedRecommenderImpl muser=new MyUserBasedRecommenderImpl();//new 一个业务层实现类来调取其中的一些关键方法
            List<RecommendedItem> listU=muser.userBasedRecommender(userId,4);//调用userBasedRecommender方法，5代表最终推荐的图书的数量
            log.info(listU.toString());
            if(listU.equals(null)){//对返回的list集合进行遍历，目的是封装图书的list集合
                List<Scenic> listByU = null;//判断集合是否为空
                //List<BookNew> listByI = null;

                model.addAttribute("recommendList",listByU);//将查到的推荐的图书集合的数据库保存在model中
                //model.addAttribute("listByI",listByI);
            }
            else {
                List<Scenic> listByU = getRecommend(listU);//调用getRecommend方法封装关于推荐图书的list集合
                //List<BookNew> listByI = getRecommend(listI);
                model.addAttribute("recommendList",listByU);//将查到的推荐的图书集合的数据库保存在model中
                //model.addAttribute("listByI",listByI);
            }

        }

        return "user-home";
    }

}
