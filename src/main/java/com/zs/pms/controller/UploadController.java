package com.zs.pms.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**文件上传控制器
 * @author Administrator
 *
 */
public class UploadController {
/**普通文件上传
 * MultipartFile file 上传的文件
 * @return文件路径
 */
@RequestMapping("/upload/common.do")
@ResponseBody
	public String commonUpload(MultipartFile file,HttpServletRequest req) {
	//UUID根据IDMac地址，时间等因素生成的永不重复的32位码
	//利用UUID算法生成前缀
	String pfix=UUID.randomUUID().toString();
	//获得文件名，任何情况都不能重名，用UUID算法生成文件名
	//生成文件名 前缀+原始文件名
	String filename=pfix+file.getOriginalFilename();
	//获得upload文件夹的物理地址
	String path=req.getRealPath("/upload");
	//创建目标文件
	File dfile=new File(path+File.separator+filename);
	try {
		//将上传的文件写入到目标文件中
		file.transferTo(dfile);
		return "filename";
	} catch (IllegalStateException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}
	}
}
