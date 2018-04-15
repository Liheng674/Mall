package com.lee.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MyFileUpload {

	public static List<String> myUpload(MultipartFile[] files) {
		String path = MyPropertiesUtil.loadProperties("myUpload.properties", "windows");
		List<String> list = new ArrayList<>();
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				String name = file.getOriginalFilename();
				if (!"".equals(name) && name != null) {
					long currentTimeMillis = System.currentTimeMillis();
					String imgName = currentTimeMillis + "_" + name;
					try {
						file.transferTo(new File(path + "/" + imgName));
						list.add(imgName);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}

}
