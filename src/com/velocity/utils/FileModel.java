package com.velocity.utils;

public class FileModel {

	@Override
	public String toString() {
		return "FileUtil [local_file_code=" + local_file_code + ", fileName=" + fileName + ", levelNum=" + levelNum + ", child=" + child + "]";
	}

	/**
	 * 文件编号
	 */
	public String local_file_code;

	/**
	 * 当前文件/文件夹名
	 */
	public String fileName;

	// /**
	// * 上级文件夹名称
	// */
	// public String parentName;
	//
	/**
	* 相对全称目录
	*/
	public String relativePath;

	/**
	* 真实全称目录
	*/
	public String realPath;

	/**
	 * 当前层级,从1开始,子元素递增
	 */
	public Integer levelNum;

	/**
	 * 子元素
	 */
	public FileModel child;

	/**
	 * 设置文件名称
	 * @param obj 文件对象
	 * @param fileName 文件名称
	 * @param levelNum 文件层级
	 * @param num 当前层级
	 */
	public static void setFileName(FileModel obj, String fileName, Integer levelNum, int num) {
		if (num > levelNum) {
			return;
		} else if (num == levelNum) {
			obj.fileName = fileName;
			obj.levelNum = levelNum;
			obj.child = null;
		} else {
			num++;
			if (obj.child == null) {
				obj.child = new FileModel();
			}
			FileModel.setFileName(obj.child, fileName, levelNum, num);
		}
	}

	/**
	 * 获取文件名称
	 * @param obj 文件对象
	 * @param flag 是否获取子元素名称
	 */
	public static String getFileName(FileModel obj, boolean flag) {
		if (obj == null) {
			return "";
		}
		if (flag) {
			String childFileName = FileModel.getFileName(obj.child, flag);
			if (!childFileName.equals("")) {
				childFileName = "/" + childFileName;
			}
			return obj.fileName + childFileName;
		} else {
			return obj.fileName;
		}
	}

	public static String getRelativePath(FileModel obj) {
		return FileModel.getFileName(obj, true);
	}

}
