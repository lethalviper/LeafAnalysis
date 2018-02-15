package pixel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Leaf {
	private int totalRed=0;
	private int totalGreen=0;
	private int totalBlue=0;
	private int totalPixel=0;
	
	private int imageHeight=0;
	private int imageWidth=0;
	
	ArrayList<Integer> finalList=null;
	
	private BufferedImage bufferedImage=null;
	Color c=null;
	public Leaf(BufferedImage bufferedImage){
		this.bufferedImage=bufferedImage;
		
		this.imageHeight=bufferedImage.getHeight();
		this.imageWidth=bufferedImage.getWidth();
	}
	
	public BufferedImage isolate(int red, int blue){		
		for(int i=0;i<imageWidth;i++){
			for(int j=0;j<imageHeight;j++){
				c = new Color(bufferedImage.getRGB(i, j));
				if((c.getGreen()>c.getRed()+red&&c.getGreen()>c.getBlue()+blue)||(c.getGreen()>c.getRed()+red&&c.getGreen()+10>c.getBlue())||(c.getGreen()>c.getBlue()+blue&&c.getGreen()+10<c.getRed())){
					totalPixel++;
					totalRed=totalRed+c.getRed();
					totalGreen=totalGreen+c.getGreen();
					totalBlue=totalBlue+c.getBlue();
				}else{
					bufferedImage.setRGB(i, j, Color.WHITE.getRGB());
				}
			}
		}
		return bufferedImage;
	}
	
	/*public static BufferedImage rotateImage(BufferedImage bi,int orientation){
		BufferedImage temp=null;
		if(orientation==1){
			temp=new BufferedImage(bi.getHeight(),bi.getWidth(),bi.getType());
			for(int i=bi.getWidth()-1;i>=0;i--){
				for(int j=bi.getHeight()-1;j>=0;j--){
					temp.setRGB(j, i, bi.getRGB(i, j));
				}
			}
		}else if(orientation==6){
			temp=new BufferedImage(bi.getWidth(),bi.getHeight(),bi.getType());
			for(int i=0;i<bi.getWidth();i++){
				for(int j=0;j<bi.getHeight();j++){
					temp.setRGB(i, j, bi.getRGB(i, j));
				}
			}
		}else if(orientation==3){
			temp=new BufferedImage(bi.getHeight(),bi.getWidth(),bi.getType());
			for(int i=0;i<bi.getWidth();i++){
				for(int j=0;j<bi.getHeight();j++){
					temp.setRGB(j, i, bi.getRGB(bi.getWidth()-(i+1), bi.getHeight()-(j+1)));
				}
			}
		}else if(orientation==8){
			temp=new BufferedImage(bi.getWidth(),bi.getHeight(),bi.getType());
			for(int i=0;i<bi.getWidth();i++){
				for(int j=0;j<bi.getHeight();j++){
					temp.setRGB(i, j, bi.getRGB(bi.getWidth()-(i+1), bi.getHeight()-(j+1)));
				}
			}
		}
		return temp;
	}*/
	
	public static BufferedImage getCopy(BufferedImage bi){	
		BufferedImage temp=new BufferedImage(bi.getWidth(),bi.getHeight(),bi.getType());
		for(int i=0;i<bi.getWidth();i++){
			for(int j=0;j<bi.getHeight();j++){
				temp.setRGB(i, j, bi.getRGB(i, j));
			}
		}
		return temp;
	}
	
	public int getMaxVerticalPixels(){
		int maxVerticalPixel=0;
		Color temp=null;
		int count=0;
		for(int i=0;i<imageWidth;i++){
			count=0;
			for(int j=0;j<imageHeight;j++){
				temp=new Color(bufferedImage.getRGB(i, j));
				if((temp.getGreen()>temp.getRed()+5&&temp.getGreen()>temp.getBlue()+5)||(temp.getGreen()>temp.getRed()+5&&temp.getGreen()+10>temp.getBlue())||(temp.getGreen()>temp.getBlue()+5&&temp.getGreen()+10<temp.getRed())){
					count++;
				}
			}
			if(count>maxVerticalPixel){
				maxVerticalPixel=count;
			}
		}
		return maxVerticalPixel;
	}
	
	public int getMaxHorizontalPixels(){
		int maxHorizontalPixel=0;
		Color temp=null;
		int count=0;
		for(int i=0;i<imageHeight;i++){
			count=0;
			for(int j=0;j<imageWidth;j++){
				temp=new Color(bufferedImage.getRGB(j, i));
				if((temp.getGreen()>temp.getRed()+5&&temp.getGreen()>temp.getBlue()+5)||(temp.getGreen()>temp.getRed()+5&&temp.getGreen()+10>temp.getBlue())||(temp.getGreen()>temp.getBlue()+5&&temp.getGreen()+10<temp.getRed())){
					count++;
				}
			}
			if(count>maxHorizontalPixel){
				maxHorizontalPixel=count;
			}
		}
		return maxHorizontalPixel;
	}
	
	public int getMaxGreen(){
		int maxGreen=0;
		Color temp=null;
		for(int i=0;i<imageWidth;i++){
			for(int j=0;j<imageHeight;j++){
				temp=new Color(bufferedImage.getRGB(i, j));
				if((temp.getGreen()>temp.getRed()+5&&temp.getGreen()>temp.getBlue()+5)||(temp.getGreen()>temp.getRed()+5&&temp.getGreen()+10>temp.getBlue())||(temp.getGreen()>temp.getBlue()+5&&temp.getGreen()+10<temp.getRed())){
					if(temp.getGreen()>maxGreen){
						maxGreen=temp.getGreen();
					}
				}
			}
		}
		return maxGreen;
	}
	
	public int getMinGreen(){
		int minGreen=255;
		Color temp=null;
		for(int i=0;i<imageWidth;i++){
			for(int j=0;j<imageHeight;j++){
				temp=new Color(bufferedImage.getRGB(i, j));
				if((temp.getGreen()>temp.getRed()+5&&temp.getGreen()>temp.getBlue()+5)||(temp.getGreen()>temp.getRed()+5&&temp.getGreen()+10>temp.getBlue())||(temp.getGreen()>temp.getBlue()+5&&temp.getGreen()+10<temp.getRed())){
					if(temp.getGreen()<minGreen){
						minGreen=temp.getGreen();
					}
				}	
			}
		}
		return minGreen;
	}
	
	public int getMaxRed(){
		int maxRed=0;
		Color temp=null;
		for(int i=0;i<imageWidth;i++){
			for(int j=0;j<imageHeight;j++){
				temp=new Color(bufferedImage.getRGB(i, j));
				if((temp.getGreen()>temp.getRed()+5&&temp.getGreen()>temp.getBlue()+5)||(temp.getGreen()>temp.getRed()+5&&temp.getGreen()+10>temp.getBlue())||(temp.getGreen()>temp.getBlue()+5&&temp.getGreen()+10<temp.getRed())){
					if(temp.getRed()>maxRed){
						maxRed=temp.getRed();
					}
				}
			}
		}
		return maxRed;
	}
	
	public int getMinRed(){
		int minRed=255;
		Color temp=null;
		for(int i=0;i<imageWidth;i++){
			for(int j=0;j<imageHeight;j++){
				temp=new Color(bufferedImage.getRGB(i, j));
				if((temp.getGreen()>temp.getRed()+5&&temp.getGreen()>temp.getBlue()+5)||(temp.getGreen()>temp.getRed()+5&&temp.getGreen()+10>temp.getBlue())||(temp.getGreen()>temp.getBlue()+5&&temp.getGreen()+10<temp.getRed())){
					if(temp.getRed()<minRed){
						minRed=temp.getRed();
					}
				}	
			}
		}
		return minRed;
	}
	
	public int getMaxBlue(){
		int maxBlue=0;
		Color temp=null;
		for(int i=0;i<imageWidth;i++){
			for(int j=0;j<imageHeight;j++){
				temp=new Color(bufferedImage.getRGB(i, j));
				if((temp.getGreen()>temp.getRed()+5&&temp.getGreen()>temp.getBlue()+5)||(temp.getGreen()>temp.getRed()+5&&temp.getGreen()+10>temp.getBlue())||(temp.getGreen()>temp.getBlue()+5&&temp.getGreen()+10<temp.getRed())){
					if(temp.getBlue()>maxBlue){
						maxBlue=temp.getBlue();
					}
				}	
			}
		}
		return maxBlue;
	}
	
	public int getMinBlue(){
		int minBlue=255;
		Color temp=null;
		for(int i=0;i<imageWidth;i++){
			for(int j=0;j<imageHeight;j++){
				temp=new Color(bufferedImage.getRGB(i, j));
				if((temp.getGreen()>temp.getRed()+5&&temp.getGreen()>temp.getBlue()+5)||(temp.getGreen()>temp.getRed()+5&&temp.getGreen()+10>temp.getBlue())||(temp.getGreen()>temp.getBlue()+5&&temp.getGreen()+10<temp.getRed())){
					if(temp.getBlue()<minBlue){
						minBlue=temp.getBlue();
					}
				}	
			}
		}
		return minBlue;
	}
	
	/*public ArrayList<Integer> curveAnalyzer(){
		Color temp=null;
		int count=0;int groupCount=0;int total=0,avg=0;int k=0;
		ArrayList<Integer> list=new ArrayList<Integer>();
		boolean flag=true;
		
		for(int i=0;i<imageWidth;i++){
			count=0;
			for(int j=0;j<imageHeight;j++){
				temp=new Color(bufferedImage.getRGB(i, j));
				if((temp.getGreen()>temp.getRed()+5&&temp.getGreen()>temp.getBlue()+5)||(temp.getGreen()>temp.getRed()+5&&temp.getGreen()+10>temp.getBlue())||(temp.getGreen()>temp.getBlue()+5&&temp.getGreen()+10<temp.getRed())){
					if(flag){
						k++;
						flag=false;
					}
					count++;
				}
			}
			total=total+count;
			if(flag==false&&k%10==0){
				groupCount++;
				avg=total/10;
				list.add(avg);
				total=0;
			}
			flag=true;
		}
		Iterator<Integer> it=list.iterator();
		finalList=new ArrayList<Integer>();
		int len=list.size();
		int i = 0;
		total=0;
		int group=(groupCount/20);

		while(it.hasNext()){
			i++;
			total=total+(int)it.next();

			if(i%group==0){
				finalList.add(total/group);
				total=0;
			}
		}

		return finalList;
	}*/
	
	public int getTotalPixel() {
		return totalPixel;
	}

	public int getTotalRed() {
		return totalRed;
	}

	public int getTotalGreen() {
		return totalGreen;
	}

	public int getTotalBlue() {
		return totalBlue;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}
}
