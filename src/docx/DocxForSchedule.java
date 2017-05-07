package docx; /**
 * Created by Yaroslav on 07.05.2017.
 */

import javafx.collections.ObservableList;
import model.Subject;
import model.SubjectRow;
import model.SubjectTime;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class DocxForSchedule {

    private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();

        if (obj.getClass().equals(toSearch))
            result.add(obj);
        else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }

        }
        return result;
    }

    private static void replaceTable(WordprocessingMLPackage template, ObservableList<Subject> subjects,
                                     ObservableList<SubjectRow> subjectRows) throws Docx4JException, JAXBException {
        List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);
        Tbl tempTable = (Tbl) tables.get(0);
        Tr row = (Tr) getAllElementFromObject(tempTable, Tr.class).get(1);


        for (int i = 0; i <subjects.size() ; i++) {
            Tr workingRow = (Tr) XmlUtils.deepCopy(row);
            List<?> rowElements = getAllElementFromObject(workingRow,Text.class);

            for(Object obj : rowElements){
                Text text=(Text) obj;

                switch (text.getValue()){
                    case"РїСЂРµРґРјРµС‚":
                        String value=subjects.get(i).getName()+"\n";
                        for(SubjectTime day:subjects.get(i).getSubDays())
                            value+=day.getWeekDay()+" "+day.getTimePlace()+" "+day.getNumOfWeek();
                        System.out.println("value "+value);
                        text.setValue(value);
                        break;
                    case"1": text.setValue(subjectRows.get(i).getWeek1()); break;
                    case"2":text.setValue(subjectRows.get(i).getWeek2()); break;
                    case"3":text.setValue(subjectRows.get(i).getWeek3()); break;
                    case"4":text.setValue(subjectRows.get(i).getWeek4()); break;
                    case"5":text.setValue(subjectRows.get(i).getWeek5()); break;
                    case"6":text.setValue(subjectRows.get(i).getWeek6()); break;
                    case"7":text.setValue(subjectRows.get(i).getWeek7()); break;
                    case"8":text.setValue(subjectRows.get(i).getWeek8()); break;
                    case"9":text.setValue(subjectRows.get(i).getWeek9()); break;
                    case"10":text.setValue(subjectRows.get(i).getWeek10()); break;
                    case"11":text.setValue(subjectRows.get(i).getWeek11()); break;
                    case"12":text.setValue(subjectRows.get(i).getWeek12()); break;
                    case"13":text.setValue(subjectRows.get(i).getWeek13()); break;
                    case"14":text.setValue(subjectRows.get(i).getWeek14()); break;
                    case"15":text.setValue(subjectRows.get(i).getWeek15()); break;
                    case"16":text.setValue(subjectRows.get(i).getWeek16()); break;
                }
            }
            tempTable.getContent().remove(1);
            tempTable.getContent().add(workingRow);
        }
    }

    public static void saveTable( ObservableList<Subject> subjects, ObservableList<SubjectRow> subjectRows,
                                  String File){
        try {
            WordprocessingMLPackage  template = WordprocessingMLPackage.load(new FileInputStream(new File("test.docx")));
            replaceTable(template,subjects,subjectRows);
            writeDocxToStream(template,File);
        } catch (Docx4JException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static void writeDocxToStream(WordprocessingMLPackage template, String target) throws IOException, Docx4JException {
        File f = new File(target);
        template.save(f);
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }


}
