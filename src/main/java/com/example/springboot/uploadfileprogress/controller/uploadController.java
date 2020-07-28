package com.example.springboot.uploadfileprogress.controller;

import com.example.springboot.uploadfileprogress.bean.InfoMsg;
import com.example.springboot.uploadfileprogress.bean.ProgressEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class uploadController {
    private static final String TMP_PATH = "e:/projects/tmp";
    /**
     * ajax 上传页面
     * @return
     */
    @RequestMapping("uploadFile")
    public String uploadFile(){
        return "uploadPage";
    }

    /**
     * layui 方式上传页面
     * @return
     */
    @RequestMapping("uploadPageForLayui")
    public String uploadFileForLayui(){
        return "uploadPageForLayui";
    }

    //对应 uploadPage 页面
    @RequestMapping("upload")
    @ResponseBody
    public InfoMsg upload(@RequestParam("uploadFile")MultipartFile file){
        InfoMsg infoMsg = new InfoMsg();
        if (file.isEmpty()){
            infoMsg.setCode("error");
            infoMsg.setMsg("Please select a file to upload");
            return infoMsg;
        }
        try {
            File tmp = new File(TMP_PATH,file.getOriginalFilename());
            if (!tmp.getParentFile().exists()){
                tmp.getParentFile().mkdirs();
            }
            file.transferTo(tmp);
            infoMsg.setCode("success");
            infoMsg.setMsg("You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
            infoMsg.setCode("error");
            infoMsg.setMsg("Uploaded file failed");
        }
        return infoMsg;
    }

    //文件上传 uploadPageForLayui 页面
    @RequestMapping(value = "uploadForLayui",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> uploadForLayui(MultipartFile file) {
        Map<String,Object> result = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        String filename = file.getOriginalFilename();
        int i = filename.lastIndexOf(".");
        String extName = filename.substring(i);
        String files = uuid+extName;
        if (file != null && !file.isEmpty()){
            try {
                file.transferTo(new File("d:/yx/"+files));
                result.put("code",200);
                result.put("msg","success");
            }catch (IOException e){
                result.put("code",-1);
                result.put("msg","文件上传出错");
                e.printStackTrace();
            }
        }else{
            result.put("code",-1);
            result.put("msg","为获取到文件");
        }
        System.out.println("输出:"+result);
        return result;
    }

    //获取进度接口
    @RequestMapping("getUploadProgress")
    @ResponseBody
    public ProgressEntity getUploadProgress(HttpServletRequest request){
        return (ProgressEntity) request.getSession().getAttribute("Status");
    }
}

