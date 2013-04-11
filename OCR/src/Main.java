import java.lang.System;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;


import java.awt.image.BufferedImage;



public class Main {
	public static void main(String args[]) throws IOException{
		int XLength = 0;
		int YLength = 0;
		
		
		ArrayList<VectorColor> testList = new ArrayList<VectorColor>();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("File path:");
		String path = scan.next();
		BufferedImage img = LoadImage(path);
		System.out.println("Loaded image, type in x and y coordinates");
		int x = scan.nextInt();
		System.out.println("");
		int y = scan.nextInt();
		System.out.println("x: " + x +  "\n"  + "y: " + y);
		Color color = new Color(GetPixelColor(img,x,y));
		System.out.println("Green: " + color.getGreen() + "  Red:  " + color.getRed() + "  Blue: " + color.getBlue());
		System.out.println("Use http://www.colorspire.com/rgb-color-wheel/ to convert RPG to color  :)  ");
		
		YLength = img.getHeight();
		XLength = img.getWidth();
		
		System.out.println("Size: X: " + XLength +  " : Y:  " + YLength);
		
		for(int i = 0; i < YLength;i++){
			for(int ii = 0; ii < XLength; ii++){
				color = new Color(GetPixelColor(img,ii,i));
				if(color.getRed() <= 55 && color.getGreen() <= 55 && color.getBlue() <= 55){
					testList.add(new VectorColor(ii, i, color.getRed(), color.getBlue(), color.getGreen(),color.getRGB()));
				}
			}
		}
		
		System.out.println("Size: " + testList.size());
		System.out.println("Red: " + testList.get(5).red + " Blue: " + testList.get(5).blue + " Green: " + testList.get(5).green);
		
		File outputfile = new File("savedDos.png");
		ImageIO.write(DrawImage(img.getHeight(),img.getWidth(),testList), "jpg", outputfile);
		
		
	}
	
	
	
	public static BufferedImage DrawImage(int hight, int width,ArrayList<VectorColor> list){
		BufferedImage imageOut = 
			    new BufferedImage(width, hight, BufferedImage.TYPE_INT_RGB);
		
		
		int size = list.size();
		
		
		// Set background to white
		for(int i = 0; i < hight;i++){
			for(int ii = 0; ii < width; ii++){
				imageOut.setRGB(ii, i, 16777215); // 16777215 = white in RGB
			}
		}
		
		
		
		for(int i = 0; i <size; i++){
			imageOut.setRGB(list.get(i).x, list.get(i).y,0);// list.get(i).RGB);  0 = svart
		}
		
		
		return imageOut;
	}
	
	public static BufferedImage LoadImage(String path) throws IOException{
		File file = new File(path);
		BufferedImage image = ImageIO.read(file);
		return image;
	}
	
	public static int GetPixelColor(BufferedImage image,int x,int y){
		return image.getRGB(x, y);
		
	}
	
	
}




