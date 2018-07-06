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
		//��ά��İ汾��
		//1 21 2 25 3 29 4 33 5 37
		//������ά��Ĵ�С63+(version-1)*12
		int imgSize=63+(version-1)*12;
		// ���ɶ�ά�����
		Qrcode qrcode = new Qrcode();
		//���ö�ά��汾��1-40���汾��Խ���ܴ��������Խ��
		qrcode.setQrcodeVersion(version);
		// ����ͼƬ�������
		BufferedImage bufferedImage = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
		//�����滭����
		Graphics2D gs = bufferedImage.createGraphics();
		//���ñ�����ɫ
		gs.setBackground(Color.white);
		//���������ɫ
		//gs.setColor(Color.cyan);
		//��ջ���
		gs.clearRect(0, 0,imgSize,imgSize);
		//��ά������
		String contest="BEGIN:VCARD\r\n"+
						"FN:����:���Ƹ�\r\n"+						
						"TEL:15713088033\r\n"+
						"ADR:��ˮ\r\n"+
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
		//ͨ����ά��Ҫ�������ݻ�ȡһ�������Ͷ�ά����
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
		//�رջ滭����
		gs.dispose();
		//��������ͼƬ������ڴ�
		bufferedImage.flush();
		ImageIO.write(bufferedImage, "png", new File(path));
		System.out.println("�ɹ�");//"D:"File.separator+
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

