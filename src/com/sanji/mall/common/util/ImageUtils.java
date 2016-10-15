/**  
 * @Title: ImageUtils.java
 * @Package com.sanji.mall.common.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ZhouZhangbao  
 * @date 2014-7-23 下午1:43:22
 * @version V1.0  
 */
package com.sanji.mall.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片工具类
 * 
 * @ClassName: ImageUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ZhouZhangbao
 * @date 2014-7-23 下午1:43:22
 */
public class ImageUtils {

	private BufferedImage image = null;
	private static ImageUtils bean = new ImageUtils();
	private static String outputFile = "d:\\aa.png";

	/**
	 * @see 以单例模式创建，获得对象实例
	 * @return Image
	 */
	public static ImageUtils getBean() {
		return bean;
	}

	public void load(File imageFile) throws IOException {
		image = ImageIO.read(imageFile);
	}

	public int getImageWidth() {
		return image.getWidth();
	}

	public int getImageHeight() {
		return image.getHeight();
	}

	// 图片裁剪
	public void cutTo(int x, int y, int tarWidth, int tarHeight) throws FileNotFoundException {
		if (image == null) {
			throw new FileNotFoundException("image file not be load.please execute 'load' function agin.");
		}

		int iSrcWidth = getImageWidth(); // 得到源图宽
		int iSrcHeight = getImageHeight(); // 得到源图长

		// 如果源图片的宽度或高度小于目标图片的宽度或高度，则直接返回出错
		if (iSrcWidth < tarWidth || iSrcHeight < tarHeight) {

			throw new RuntimeException("source image size too small.");
		}

		// 先求源图和目标图的尺寸比例
		double dSrcScale = iSrcWidth * 1.0 / iSrcHeight;
		double dDstScale = tarWidth * 1.0 / tarHeight;

		// 先确定剪裁尺寸
		int iDstLeft, iDstTop, iDstWidth, iDstHeight;
		if (dDstScale > dSrcScale) { // 目标图片宽
			iDstWidth = iSrcWidth;
			iDstHeight = (int) (iDstWidth * 1.0 / dDstScale);
		} else { // 目标图片高
			iDstHeight = iSrcHeight;
			iDstWidth = (int) (iDstHeight * dDstScale);
		}
		iDstLeft = (iSrcWidth - iDstWidth) / 2;
		iDstTop = (iSrcHeight - iDstHeight) / 2;

		// 剪裁
		this.image = image.getSubimage(x, y, tarWidth, tarHeight);

	}

	/**
	 * 图片缩放 不生成新的图片
	 * 
	 * @Title: zoomTo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param tarWidth
	 * @param @param tarHeight 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	public void zoomTo(int tarWidth, int tarHeight) {
		BufferedImage tagImage = new BufferedImage(tarWidth, tarHeight, BufferedImage.TYPE_INT_RGB); // 缩放图像
		Image image = this.image.getScaledInstance(tarWidth, tarHeight, Image.SCALE_SMOOTH);
		Graphics g = tagImage.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制目标图
		g.dispose();
		this.image = tagImage;
	}

	/**
	 * 缩放图片 生成新的图片
	 * 
	 * @Title: zoomImage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param srcFile
	 * @param @param dstFile
	 * @param @param width
	 * @param @param height
	 * @param @param formatName
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public static String zoomImage(String srcFile, String dstFile, int width, int height, String formatName) {
		try {
			ImageUtils zoom = new ImageUtils();
			zoom.load(new File(srcFile));
			zoom.zoomTo(width, height);
			zoom.save(dstFile, formatName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dstFile;
	}

	/**
	 * 缩放图片 生成新的图片
	 * 
	 * @Title: zoomImage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param yFile
	 * @param @param dstFile
	 * @param @param width
	 * @param @param height
	 * @param @param formatName
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author ZhouZhangbao
	 */
	public static String zoomImage(File yFile, String dstFile, int width, int height, String formatName) {
		try {
			ImageUtils zoom = new ImageUtils();
			zoom.load(yFile);
			zoom.zoomTo(width, height);

			zoom.save(dstFile, formatName);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dstFile;
	}

	public static void main(String[] args) {
		// 从硬盘读取一个文件
		// File file = new File("D://1.jpg");

		ImageUtils zoom = new ImageUtils();
		// zoom.zoomImage(file, "D://s_1.jpg", 10, 10, "png");
		// 调用方法zoomImage(File yFile, String dstFile, int width, int height,
		// String formatName)

	}

	public String saveImg(File file, String filePath, String fileName) throws Exception {
		InputStream in = new FileInputStream(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		in.close();

		File newFile = new File(filePath + fileName);
		FileOutputStream fops = new FileOutputStream(newFile);
		fops.write(buffer);
		fops.flush();
		fops.close();

		return filePath + fileName;

	}

	/**
	 * 保存
	 * 
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param fileName
	 * @param @param formatName
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	public void save(String fileName, String formatName) throws IOException {
		// 写文件
		FileOutputStream out = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(this.image, formatName, bos);// 输出到bos
			out = new FileOutputStream(fileName);
			out.write(bos.toByteArray()); // 写文件
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static ImageUtils fromImageFile(File file) throws IOException {
		// System.out.print(">>>>>>>>>>>>>>>>>>>>"+file.exists());
		ImageUtils utils = new ImageUtils();
		utils.load(file);
		return utils;
	}

	/**
	 * 加载图片
	 * 
	 * @Title: load
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param fileName
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return ImageUtils 返回类型
	 * @author ZhouZhangbao
	 */
	public static ImageUtils load(String fileName) throws IOException {
		File file = new File(fileName);
		return fromImageFile(file);
	}

	/**
	 * 绘制水印
	 * 
	 * @Title: water
	 * @Description: TODO(水印)
	 * @param @param imgPath 图片路径(物理路径)
	 * @param @param waterPath 水印图片路径(物理路径)
	 * @return void 返回类型
	 * @author ZhouZhangbao
	 */
	public static void water(String imgPath, String waterPath) {
		try {
			String extend = imgPath.substring(imgPath.lastIndexOf("."));
			if (!".gif".toLowerCase().equals(extend)) {// 如果图片不是gif类型就加水印
				BufferedImage src = ImageIO.read(new File(imgPath));
				int width = src.getWidth();
				int height = src.getHeight();
				BufferedImage water = ImageIO.read(new File(waterPath));
				int waWidth = water.getWidth();
				int waHeight = water.getHeight();
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics graph = image.createGraphics();
				graph.drawImage(src, 0, 0, width, height, null);
				graph.drawImage(water, (width - waWidth) / 2, (height - waHeight) / 2, waWidth, waHeight, null);
				graph.dispose();
				OutputStream out = new FileOutputStream(imgPath);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(image);
				out.flush();
				out.close();
				image.flush();
				water.flush();
				src.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main2(String[] args) {

		// bean.water("D:\\2.jpg", bean.outputFile);// 绘制水印

		String p = "http://www.qlqpw.com/companyImg/img/c4bf5ab8e6614361a1b988fab9bb9cbc.JPG";
		ImageUtils image;
		// image = ImageUtils.load(p);
		// ImageUtils.zoomImage(p, "d:/e1.jpg", 240, 150, "jpg");
		// image.zoomTo(500, 500);
		// image.cutTo(50, 50, 100, 100);
		// image.save("e:/111.jpg", "jpg");
	}

}
