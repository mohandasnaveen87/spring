package excel;
	import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import com.mongodb.
	public class ReadExcelFileDemo  
	{  
	@SuppressWarnings("deprecation")
	public static void main(String[] args)   
	{  
	try  
	{  
	File file = new File("C:\\Users\\14439\\OneDrive\\Desktop\\Book1.xlsx");   //creating a new file instance  
	FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
	//creating Workbook instance that refers to .xlsx file  
	XSSFWorkbook wb = new XSSFWorkbook(fis);   
	XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
	Iterator<Row> itr = sheet.iterator();  //iterating over excel file
	
	List <Employee> empList=new ArrayList<Employee>();
	while (itr.hasNext())                 
	{  
		Row row = itr.next();  
		Employee emp=new Employee();
		//Iterator<Cell> cellIterator = row.cellIterator();  //iterating over each column
		emp.setEmployeeId(row.getCell(0).getNumericCellValue());
		emp.setEmployeeName(row.getCell(1).getStringCellValue());
		emp.setSalary(row.getCell(2).getNumericCellValue());
		emp.setDesignation(row.getCell(3).getStringCellValue());
		emp.setDepartment(row.getCell(4).getStringCellValue());
		
		empList.add(emp);
	}  
	Iterator<Employee> empItr=empList.iterator();
	
	while(empItr.hasNext()) {
		
		Employee emp=(Employee)empItr.next();
		System.out.println(emp.getEmployeeId()+""+emp.getEmployeeName()+""+emp.getSalary()+""+emp.getDesignation()+""+emp.getDepartment());
		System.out.println("\n");
	}
		  
	 MongoClient mongo = new MongoClient("localhost", 27017);
	 MongoDatabase  db = mongo.getDatabase("test");
     MongoCollection collection = db.getCollection("employee");
   
     List<Document> docLst= new ArrayList<Document>();
     
     ObjectMapper obj =new ObjectMapper();
     for (Employee e:empList) {	 
    	 
    	 docLst.add(Document.parse(obj.writeValueAsString(e)));
     }
     
    collection.insertMany(docLst);
		}  
	catch(Exception e)  
		{  
		e.printStackTrace();  
		}  
	}  
		}  