package kq.qh.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import kq.qh.common.Excel;

public class ExcelUtil {
	
	/**
	 * 写Excel
	 * @param l  需要写入的列表集合
	 * @param fileName  文件名
	 * @param cols  表头 ：     @param clazz 中的属性
	 * @param clazz  列表中存储的数据的类型
	 * @param response @HttpServletResponse 对象
	 */
	public void writeExcel(List<?> l,String fileName,String[] cols,Class<?> clazz, HttpServletResponse response){
		WritableWorkbook book = null;
		OutputStream out = null;
        // 
		try { 
			out = response.getOutputStream();
	        book = Workbook.createWorkbook(out);
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String[] header = new String[cols.length];
			for(int i=0;i<cols.length;i++){
				//通过注解类Excel中的name 获取 导出excel的表头
				String colName = clazz.getDeclaredField( cols[i] ).getAnnotation(Excel.class).value();
				header[i] = colName;
			}
			sheet = createModel(header,fileName,sheet);
			for(int i=0;i<cols.length;i++){
				int max = 0;
				for(int j=0;j<l.size();j++){
					Field field = l.get(j).getClass().getDeclaredField(cols[i]);
					field.setAccessible(true);
					int temp = 0;
					try {
						if(null != field.get(l.get(j)) ){
							String value = field.get(l.get(j)).toString();
							if(field.getType().getSimpleName().contains("Integer")){
								jxl.write.Number number= new jxl.write.Number(j,i+2,Integer.parseInt(value));
								sheet.addCell(number);
							}else if(field.getType().getSimpleName().contains("Long")){
								jxl.write.Number number= new jxl.write.Number(j,i+2,Long.parseLong(value));
								sheet.addCell(number);
							}else if(field.getType().getSimpleName().contains("Double")){
								jxl.write.Number number= new jxl.write.Number(j,i+2,Double.parseDouble(value));
								sheet.addCell(number);
							}else if(field.getType().getSimpleName().contains("float")){
								jxl.write.Number number= new jxl.write.Number(j,i+2,Float.parseFloat(value));
								sheet.addCell(number);
							}else if(field.getType().getSimpleName().contains("Date")){
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								jxl.write.Label label = new Label(j, i+2, sdf.format(value));
								sheet.addCell(label);
							}else{
								jxl.write.Label label = new Label(i, j+2, value);
								sheet.addCell(label);
							}
						}
						temp = (field.get(l.get(j))+"").getBytes().length+1;
					} catch (Exception e) {
						e.printStackTrace();
					}
					max = max>temp?max:temp;
					sheet.setColumnView(i,max);
				}
			}
			sheet = createFooter(header.length,l.size()+2,sheet);
		    response.setContentType("application/vnd.ms-excel"); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			String filename = sdf.format(new Date())+fileName + ".xls"; 
			filename = new String(filename.getBytes("utf-8"),"iso-8859-1");//导出中文文件名，转编码
			response.setHeader("Content-disposition", "attachment;filename="+ filename);			
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				book.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	

	private WritableSheet createModel(String[] header,String fileName,WritableSheet sheet){
		try {
			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setAlignment(Alignment.CENTRE);//单元格内容停靠方式（居中）
			sheet.mergeCells(0, 0, header.length-1, 0);
			Label label = new Label(0,0,fileName);
			label.setCellFormat(wcf);
			sheet.addCell(label);
			for(int i=0;i<header.length;i++){
				Label label1 = new Label(i,1,header[i]);
				sheet.addCell(label1);
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return sheet;
	}
	
	private WritableSheet createFooter(int column,int row,WritableSheet sheet){
		String time = DateUtil.format(new Date(),"yyyy-MM-dd");
		
		try {
			WritableCellFormat wcf = new WritableCellFormat();
			wcf.setAlignment(Alignment.RIGHT);//单元格内容停靠方式（居中）
			sheet.mergeCells(0, row+1, column-1, row+1);
			Label label = new Label(0,row+1, time);
			label.setCellFormat(wcf);
			sheet.addCell(label);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return sheet;
	}
	public static void main(String[] args) {
		
	}
	
	/**
	 * 
	 * @param l  数组的集合
	 * @param header 表头
	 * @param fileName 文件名
	 * @param response
	 */
	public void writeExcel(List<String[]> l,String[] header,String fileName, HttpServletResponse response){
		WritableWorkbook book = null;
		OutputStream out = null;
		try { 
			out = response.getOutputStream();
	        book = Workbook.createWorkbook(out);  
			WritableSheet sheet = book.createSheet("sheet1", 0);
			sheet = createModel(header,fileName,sheet);
			for(int i=0;i<l.size();i++){
				for(int j=0;j<header.length;j++){
					String value = null == l.get(i)[j]?"0":l.get(i)[j];
					if(value.matches("^[0-9]+?$")){//将只存在0-9的字符串生成excel转成数值类型
						jxl.write.Number number= new jxl.write.Number(j,i+2,Integer.parseInt(value));
						sheet.addCell(number);
					}else{
						Label label = new Label(j,i+2,value);
						sheet.addCell(label);
					}
				}
			}
			sheet = createFooter(header.length,l.size()+2,sheet);
		    response.setContentType("application/vnd.ms-excel"); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			String filename = sdf.format(new Date())+fileName + ".xls"; 
			filename = new String(filename.getBytes("utf-8"),"iso-8859-1");//中文名转编码
			response.setHeader("Content-disposition", "attachment;filename="+ filename);			
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				book.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * 读取excel转化为实体类  @param clazz   
	 * <p>jxl只能读取 Office Excel 2003</p>
	 * @param in 
	 * @param clazz
	 * @param in
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List readerExcel(Class clazz,InputStream in){
		Workbook book = null;
		List list = new ArrayList();
		try {
			book = Workbook.getWorkbook(in);
			Sheet sheet = book.getSheet(0);
			int column_length = sheet.getColumns();
			int row_length = sheet.getRows();
			Field[] field = clazz.getDeclaredFields();
			for(int i=1;i<row_length;i++){
				//反射，动态实例化
				Object obj = clazz.newInstance();
				for(int j = 0;j<column_length;j++){
					for(Field f:field){
						f.setAccessible(true);
						if(f.getName().equals(sheet.getCell(j, 0).getContents())){
							if(!sheet.getCell(j, i).getContents().equals("null")&&!sheet.getCell(j, i).getContents().equals("")){
								if(f.getType().getSimpleName().contains("Integer")||f.getType().getSimpleName().contains("int")){
									f.set( obj, Integer.parseInt( sheet.getCell(j, i).getContents() )  );
								}else if(f.getType().getSimpleName().contains("Long")||f.getType().getSimpleName().contains("long")){
									f.set( obj, Long.parseLong( sheet.getCell(j, i).getContents() )  );
								}else if(f.getType().getSimpleName().contains("Double")||f.getType().getSimpleName().contains("double")){
									f.set( obj, Double.parseDouble( sheet.getCell(j, i).getContents() )  );
								}else if(f.getType().getSimpleName().contains("float")||f.getType().getSimpleName().contains("Float")){
									f.set( obj, Float.parseFloat( sheet.getCell(j, i).getContents() )  );
								}else if(f.getType().getSimpleName().contains("Date")){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date d = sdf.parse(sheet.getCell(j, i).getContents() );
									f.set( obj, d );
								}else{
									f.set( obj,  sheet.getCell(j, i).getContents()   );
								}
							}
						}
					}					
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	
}
