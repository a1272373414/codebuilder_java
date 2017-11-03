package com.velocity.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.velocity.entity.TableModel;
import com.velocity.helper.FileHelper;
import com.velocity.helper.GeneratorVelocityHelper;
import com.velocity.utils.FileModel;

/**
 * 测试类文件
 * @author neo
 * @since 2013-01-01
 */
public class Example {
	public static void main(String[] args) {
		File f = new File(Example.class.getResource("/").getPath() + File.separator + "template_app" + File.separator);
		FileModel fielModel = new FileModel();
		TableModel tableModel2 = GeneratorVelocityHelper.databaseParam.tableList.get(0);
		List<TableModel> list = new ArrayList<TableModel>();
		list.add(tableModel2);
		GeneratorVelocityHelper.databaseParam.tableList = list;

		for (TableModel tableModel : GeneratorVelocityHelper.databaseParam.tableList) {
			
			FileHelper.start(f, fielModel, 1, tableModel);
		}
	}
}
