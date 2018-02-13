package test;

import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;

import pixel.*;

public class ImageTest {
	public static void main(String args[]) throws Exception{
		if(args.length==0||args.length>2){
			System.out.println("Run - ImageTest <filename.jpg> <description of leaf/image>");
			System.exit(0);
		}else{
			File inputFile=new File(args[0]);
			
			if(inputFile.getName().endsWith("jpg")){
				File csvFile = new File("outputRecords.csv");
				
				FileOutputStream fos = new FileOutputStream(csvFile,true);
				OutputStreamWriter osw = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(osw);
				String record="";
				
				BufferedImage bufferedImage=ImageIO.read(inputFile);
				
				Metadata metadata = ImageMetadataReader.readMetadata(inputFile);
		        ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
		        JpegDirectory jpegDirectory = (JpegDirectory) metadata.getFirstDirectoryOfType(JpegDirectory.class);

		        int orientation = 1;
		        try {
		            orientation = exifIFD0Directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }				
				
		        //---------------------------------------------------------
				bufferedImage=Leaf.rotateImage(bufferedImage, orientation);
				//---------------------------------------------------------
				
				Leaf leaf=new Leaf(bufferedImage);
				
				bufferedImage=leaf.isolate(5,5);
				
				int height = leaf.getImageHeight();
				int width = leaf.getImageWidth();
				
				int totalRed = leaf.getTotalRed();
				int totalGreen = leaf.getTotalGreen();
				int totalBlue = leaf.getTotalBlue();
				int totalPixel = leaf.getTotalPixel();
				int avgRed = totalRed/totalPixel;
				int avgGreen = totalGreen/totalPixel;
				int avgBlue = totalBlue/totalPixel;
				
				int maxHP=leaf.getMaxHorizontalPixels();
				int maxVP=leaf.getMaxVerticalPixels();
				double ratioHV=(double)maxHP/(double)maxVP;
				
				int maxRed=leaf.getMaxRed();
				int minRed=leaf.getMinRed();
				int maxGreen=leaf.getMaxGreen();
				int minGreen=leaf.getMinGreen();
				int maxBlue=leaf.getMaxBlue();
				int minBlue=leaf.getMinBlue();
				
				ArrayList<Integer> arrayList=leaf.curveAnalyzer();
				
				int d1=arrayList.get(18)-arrayList.get(19);
				int d2=arrayList.get(17)-arrayList.get(18);
				int d3=arrayList.get(16)-arrayList.get(17);
				int d4=arrayList.get(15)-arrayList.get(16);
				int d5=arrayList.get(14)-arrayList.get(15);
				int d6=arrayList.get(13)-arrayList.get(14);
				int d7=arrayList.get(12)-arrayList.get(13);
				int d8=arrayList.get(11)-arrayList.get(12);
				int d9=arrayList.get(10)-arrayList.get(11);
				int d10=arrayList.get(9)-arrayList.get(10);
				int d11=arrayList.get(8)-arrayList.get(9);
				int d12=arrayList.get(7)-arrayList.get(8);
				int d13=arrayList.get(6)-arrayList.get(7);
				int d14=arrayList.get(5)-arrayList.get(6);
				int d15=arrayList.get(4)-arrayList.get(5);
				int d16=arrayList.get(3)-arrayList.get(4);
				int d17=arrayList.get(2)-arrayList.get(3);
				int d18=arrayList.get(1)-arrayList.get(2);
				int d19=arrayList.get(0)-arrayList.get(1);
				File outputFile=new File("output.jpg");
				
				ImageIO.write(bufferedImage, "jpg", outputFile);
				if(csvFile.length()==0){
					record="FileName,LeafDescription,Height,Width,AvgRed,AvgGreen,AvgBlue,MaxHorizontalPixel,MaxVerticalPixel,ratioHVP";
					record=record+",widthd1,widthd2,widthd3,widthd4,widthd5,widthd6,widthd7,widthd8,widthd9,widthd10";
					record=record+",widthd11,widthd12,widthd13,widthd14,widthd15,widthd16,widthd17,widthd18,widthd19";
					bw.write(record);
				}
				record=inputFile.getName()+","+args[1]+","+height+","+width+","+avgRed+","+avgGreen+","+avgBlue+","+maxHP+","+maxVP+","+ratioHV;
				record=record+","+d1+","+d2+","+d3+","+d4+","+d5+","+d6+","+d7+","+d8+","+d9+","+d10;
				record=record+","+d11+","+d12+","+d13+","+d14+","+d15+","+d16+","+d17+","+d18+","+d19;
				bw.newLine();
				bw.write(record);
				
				
				bw.close();
				osw.close();
				fos.close();
			}
		}
	}
}
