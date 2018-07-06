package wyg;
import java.awt.Color;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class w1 {
	int version=25;
	String star;
	String end;
	String path;
	
	public static void Createqrcode(int version,String path,String star,String end) throws IOException{
		//二维码的版本号
		//1 21 2 25 3 29 4 33 5 37
		//声明二维码的大小63+(version-1)*12
		int imgSize=63+(version-1)*12;
		// 生成二维码对象
		Qrcode qrcode = new Qrcode();
		//设置二维码版本号1-40，版本号越大能储存的内容越多
		qrcode.setQrcodeVersion(version);
		// 生成图片缓存对象
		BufferedImage bufferedImage = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
		//创建绘画对象
		Graphics2D gs = bufferedImage.createGraphics();
		//设置背景颜色
		gs.setBackground(Color.white);
		//设置填充颜色
		//gs.setColor(Color.cyan);
		//清空画板
		gs.clearRect(0, 0,imgSize,imgSize);
		//二维码内容
		String contest="BEGIN:VCARD\r\n"+
						"FN:姓名:王云港\r\n"+						
						"TEL:15713088033\r\n"+
						"ADR:衡水\r\n"+
						"END:VCARD";					;
		int starR=0,starG=0,starB=0;
		if(null!= star)
		{
			String[] startcolor=star.split(",");
			starR=Integer.valueOf(startcolor[0]);
			starG=Integer.valueOf(startcolor[1]);
			starB=Integer.valueOf(startcolor[2]);			
		}
		int endR=0,endG=0,endB=0;
		if(null!= end)
		{
			String[] endcolor=end.split(",");
			endR=Integer.valueOf(endcolor[0]);
			endG=Integer.valueOf(endcolor[1]);
			endB=Integer.valueOf(endcolor[2]);			
		}
		//通过二维码要填充的内容获取一个布尔型二维数组
		boolean[] []calQrcode=qrcode.calQrcode(contest.getBytes());
		for (int i= 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode.length; j++) {
				if(calQrcode[i][j]){
					int r=starR+(endR-starR)*(j+1)/calQrcode.length ;
					int g=starG+(endG-starG)*(j+1)/calQrcode.length ;
					int b=starB+(endB-starB)*(j+1)/calQrcode.length;					
					Color cc = new Color(r,g,b);
					gs.setColor(cc);
					gs.fillRect(i*3,j*3,3,3);
				}
			}
		}
		BufferedImage logo=ImageIO.read(new File("D:/ww.png"));
		int logoSize=imgSize/3;
		int o=(imgSize-logoSize)/2;
		gs.drawImage(logo, o, o, logoSize,logoSize,null);
		qrcode.setQrcodeErrorCorrect('m');
		//关闭绘画对象
		gs.dispose();
		//将缓存区图片输出到内存
		bufferedImage.flush();
		ImageIO.write(bufferedImage, "png", new File(path));
		System.out.println("成功");//"D:"File.separator+
	}

	public static void main(String[] args) throws IOException {
			w1 c=new w1();
		Scanner reader=new Scanner(System.in);	
		
		    c.version=10;
		    c.star="255,65,84";
		    c.end="240,160,100";
		    c.path ="D:/a.png";
			c.Createqrcode(c.version,c.path,c.star,c.end);
}
}

