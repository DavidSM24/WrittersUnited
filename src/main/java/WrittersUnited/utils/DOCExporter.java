package WrittersUnited.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import WrittersUnited.models.Chapter;
import WrittersUnited.models.Project;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DOCExporter {

	public static void export_To_Word(Project p, String fileName) throws IOException {

		try {
			File file = new File(fileName);

			FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());

			XWPFDocument doc = new XWPFDocument();
			XWPFParagraph tempParagraph = doc.createParagraph();
			XWPFRun maintitle = tempParagraph.createRun();

			tempParagraph.setAlignment(ParagraphAlignment.CENTER);

			maintitle.setFontSize(16);
			maintitle.setBold(true);
			maintitle.setText(p.getTitle());

			maintitle.addBreak();
			maintitle.addBreak();

			List<Chapter> list = new ArrayList<Chapter>();
			list.addAll(p.getChapters());

			for (Chapter c : list) {

				// Chapter c=list.get(i);

				XWPFParagraph cparagraph = doc.createParagraph();

				cparagraph.setAlignment(ParagraphAlignment.LEFT);

				XWPFRun ctitle = cparagraph.createRun();

				ctitle.setFontSize(16);
				ctitle.setBold(true);
				ctitle.setText("Capítulo " + c.getNumber() + ": " + c.getTitle());

				ctitle.addBreak();

				XWPFParagraph cparagraph2 = doc.createParagraph();

				cparagraph2.setAlignment(ParagraphAlignment.THAI_DISTRIBUTE);

				XWPFRun cbody = cparagraph2.createRun();

				cbody.setFontSize(13);
				cbody.setText(c.getBody());

				cbody.addBreak();
				cbody.addBreak();

			}

			doc.write(fos);
			fos.close();
			
			if (file.canWrite()) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Información");
				alert.setContentText("¡" + p.getTitle() + " se ha exportado correctamente a doc!");
				alert.showAndWait();
			}


		} catch (Exception e) {

		}
	}

}
