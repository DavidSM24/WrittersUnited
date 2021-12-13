package WrittersUnited.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DOCExporter {
	
    //Create Word
    public static void createWord(String fileName) throws IOException {
    	try {
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());

			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph tempParagraph = doc.createParagraph();
			XWPFRun tempRun = tempParagraph.createRun();

			tempRun.setText("This is a Paragraph");
			tempRun.setFontSize(12);
			doc.write(fos);
			fos.close();
			
			System.out.println(file.getAbsolutePath()+ " created successfully!");

		} catch (Exception e) {

		}
    }
    
    public static void testcreateWord(String fileName) throws IOException {
    	try {
    		File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());

			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph tempParagraph = doc.createParagraph();
			XWPFRun tempRun = tempParagraph.createRun();

			for(int i=0;i<3;i++) {
				tempRun.setText("This is a Paragraph");
	
				tempRun.addBreak();
				tempRun.setText("\nThis is a Paragraph 2");
				tempRun.setFontSize(12);
				
			}
			
			doc.write(fos);
			fos.close();
			
			System.out.println(file.getAbsolutePath()+ " created successfully!");

		} catch (Exception e) {

		}
    }
	
}
