package com.aspose.gridweb.test.servlet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
//import com.aspose.cells.BorderStyle;
//import com.aspose.cells.Color;
//import com.aspose.cells.FontUnit;
//import com.aspose.cells.GridCells;
//import com.aspose.cells.GridHyperlink;
//import com.aspose.cells.GridHyperlinkCollection;
//import com.aspose.cells.GridValidation;
//import com.aspose.cells.GridValidationCollection;
//import com.aspose.cells.GridValidationType;
//import com.aspose.cells.GridWebBean;
//import com.aspose.cells.GridWorksheet;
//import com.aspose.cells.GridWorksheetCollection;
//import com.aspose.cells.HorizontalAlign;
//import com.aspose.cells.OperatorType;
//import com.aspose.cells.Unit;
//import com.aspose.cells.WebBorderStyle;
//import com.aspose.cells.GridCustomServerValidation;
//import com.aspose.cells.GridCellArea;
import com.aspose.gridweb.*;
import com.aspose.gridweb.test.TestGridWebBaseServlet;

/**
 * import modes.jsp,data_validation.jsp,create_content.jsp
 */
  class MyCommandEventHandler implements CustomCommandEventHandler,Serializable{
	 
 
	private static final long serialVersionUID = 1L;

	@Override
	public void handleCellEvent(Object sender, String command) {
		GridWebBean gridweb = (GridWebBean) sender;
		System.out.print("get command:"+command+"  "+gridweb.getActiveSheet().getName());
		gridweb.getActiveSheet().getCells().get("F9").putValue(command);
		
	}

}
class myservervali implements GridCustomServerValidation
{

	@Override
	public String validate(GridWorksheet arg0, int row, int col, String v) {
		 if (row %2== 1)
         {
             return "not passed";
             
         }

         else
         {
             return "";
         }
	}
	
}
public class FunctionServlet extends TestGridWebBaseServlet {
	private static final long serialVersionUID = 1L;

	 
	
	 
	
	@Override
	public void reload(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		 
		try {
			super.reloadfile(gridweb,request,"data.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showEditor(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		boolean isshow = Boolean.valueOf(request.getParameter("isshow"));
		
		gridweb.setShowCellEditBox(isshow);
	}

	public void editMode(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		boolean editMode = Boolean.valueOf(request.getParameter("editMode"));
		
		gridweb.setEditMode(editMode);
	}
	
	public void setRowReadonly(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		boolean mode = Boolean.valueOf(request.getParameter("editMode"));
		int row=Integer.parseInt(request.getParameter("row"));
		gridweb.getActiveSheet().setRowReadonly(row, mode);
	}
	
	public void setColReadonly(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		boolean mode = Boolean.valueOf(request.getParameter("editMode"));
		int col=Integer.parseInt(request.getParameter("col"));
		gridweb.getActiveSheet().setColumnReadonly(col, mode);
	}
	
	public void setBorders(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		 
		int firstRow=Integer.parseInt(request.getParameter("firstrow"));
		int firstColumn=Integer.parseInt(request.getParameter("firstcol"));
		int rowNumber=Integer.parseInt(request.getParameter("rows"));
		int columnNumber=Integer.parseInt(request.getParameter("cols"));
		int borderposition= Integer.parseInt(request.getParameter("borderposition"));
		String bordercolorname= request.getParameter("bordercolorname");
		int borderlinetype=Integer.parseInt(request.getParameter("borderlinetype"));
		 
		// webborder
		 
		WebBorderStyle wbs=new WebBorderStyle();
		wbs.setBorderStyle(borderlinetype);
		wbs.setBorderWidth(Unit.Pixel(1));
		wbs.setBorderColor(Color.fromName(bordercolorname));
		gridweb.getWorkSheets().get(0).getCells().setBorders(firstRow, firstColumn, rowNumber, columnNumber, borderposition, wbs);
	}

	public void loadHyperlinkFile(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		 

		// reload the new file
		try {
			super.reloadfile(gridweb,request,"hyperlink.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}
		gridweb.setWidth(Unit.Pixel(600));
		gridweb.setHeight(Unit.Pixel(400));
		// the first sheet
		GridWorksheet firstSheet = gridweb.getWorkSheets().get(0);
		GridHyperlinkCollection hyperlinkCollection = firstSheet.getHyperlinks();
		GridHyperlink B1 = hyperlinkCollection.get(hyperlinkCollection.add("B1", "B1", "http://www.aspose.com", "Aspose site",
				"Go to Aspose site and open in new window"));
		B1.setTarget("_blank");

		GridHyperlink B2 = hyperlinkCollection.get(hyperlinkCollection.add("B2", "B2",  request.getRequestURL().toString().replace("FunctionServlet", "pagination"),
				"Paginatind sheet Demo", "Go to Aspose site and open in current window"));
		B2.setTarget("_self");

		GridHyperlink B3 = hyperlinkCollection.get(hyperlinkCollection.add("B3", "B3",
								"http://www.aspose.com/categories/.net-components/aspose.cells-for-.net/default.aspx",
				"Aspose.Cells.GridWeb Product", "Go to Aspose site and open in top window"));
		B3.setTarget("_top");

		GridHyperlink B4 = hyperlinkCollection.get(hyperlinkCollection.add("B4", "B4",
				"http://www.aspose.com/Community/Forums/258/ShowForum.aspx", "Aspose.Cells.GridWeb Forums",
				"Go to Aspose site and open in new window"));
		B4.setTarget("_parent");

		GridHyperlink B6 = hyperlinkCollection.get(hyperlinkCollection.add("B6", "B6", "http://www.aspose.com", "Aspose site",
				"Go to Aspose site and open in new window"));
		B6.setImageURL( "images/Aspose.Banner.gif");

		GridHyperlink B7 = hyperlinkCollection.get(hyperlinkCollection.add("B7", "B7",
								"http://www.aspose.com/categories/.net-components/aspose.cells-for-.net/default.aspx",
				"Go to Aspose.Cells.GridWeb site and open in new window", "Go to Aspose site and open in new window"));
		B7.setImageURL( "images/Aspose.Grid.gif");

		GridHyperlink B8 = hyperlinkCollection.get(hyperlinkCollection.add("B8", "B8", "", "", "A simple CellImage."));
		B8.setImageURL("images/Aspose.Grid.gif");
		firstSheet.getCells().get("A8").setValue("Creates a CellImage:");
		firstSheet.getCells().setRowHeightPixel(7, 150);
	}

	public void loadCreateContentFile(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		this.reload(gridweb,request, response);
		gridweb.setShowCellEditBox(true);
		 
		gridweb.getWorkSheets().add("first");
		 
	}

	public void createContent(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 
		GridWorksheetCollection gridWorksheetCollection = gridweb.getWorkSheets();
		GridWorksheet gridWorksheet = gridWorksheetCollection.add("invoice");
		GridCells gridCells = gridWorksheet.getCells();

		// cell Head
		this.createContentHead(gridCells, 0, 0, "Order ID");
		this.createContentHead(gridCells, 0, 1, "Customer ID");
		this.createContentHead(gridCells, 0, 2, "Salesperson");
		this.createContentHead(gridCells, 0, 3, "Order Date");
		this.createContentHead(gridCells, 0, 4, "Ship Via");

		// cell body
		gridCells.get(1, 0).setValue("11077");
		gridCells.get(1, 0).getStyle().setHorizontalAlign(HorizontalAlign.Right);
		gridCells.get(1, 1).setValue("RATTC");
		gridCells.get(1, 1).getStyle().setHorizontalAlign(HorizontalAlign.Center);
		gridCells.get(1, 2).setValue("Nancy Davolio");
		gridCells.get(1, 2).getStyle().setHorizontalAlign(HorizontalAlign.Center);
		gridCells.get(1, 3).setValue(new Date());
		gridCells.get(1, 3).getStyle().setHorizontalAlign(HorizontalAlign.Right);
		gridCells.get(1, 3).setNumberType(15);
		gridCells.get(1, 4).setValue("United Package");
		gridCells.get(1, 4).getStyle().setHorizontalAlign(HorizontalAlign.Center);

		gridCells.get(2, 0).setValue("11076");
		gridCells.get(2, 0).getStyle().setHorizontalAlign(HorizontalAlign.Right);
		gridCells.get(2, 1).setValue("BONAP");
		gridCells.get(2, 1).getStyle().setHorizontalAlign(HorizontalAlign.Center);
		gridCells.get(2, 2).setValue("Margaret Peacock");
		gridCells.get(2, 2).getStyle().setHorizontalAlign(HorizontalAlign.Center);
		gridCells.get(2, 3).setValue(new Date());
		gridCells.get(2, 3).getStyle().setHorizontalAlign(HorizontalAlign.Right);
		gridCells.get(2, 4).setValue("United Package");
		gridCells.get(2, 4).getStyle().setHorizontalAlign(HorizontalAlign.Center);

		// gridCells.setColumnWidth(1, 80);
		// gridCells.setColumnWidth(2, 120);
		// gridCells.setColumnWidth(3, 120);
		// gridCells.setColumnWidth(4, 120);
		//
		// gridCells.setRowHeight(0, 20);

		gridWorksheetCollection.setActiveSheetIndex(gridWorksheet.getIndex());
	}

	private void createContentHead(GridCells gridCells, int x, int y, String value) {
		gridCells.get(x, y).setValue(value);
		gridCells.get(x, y).getStyle().getFont().setSize(new FontUnit("10pt"));
		gridCells.get(x, y).getStyle().getFont().setBold(true);
		gridCells.get(x, y).getStyle().setForeColor(Color.getBlue());
		gridCells.get(x, y).getStyle().setBackColor(Color.getAqua());
		gridCells.get(x, y).getStyle().setHorizontalAlign(HorizontalAlign.Center);
		gridCells.get(x, y).getStyle().setBorderStyle(BorderStyle.Double);
		gridCells.get(x, y).getStyle().setBorderColor(Color.getGold());
		gridCells.get(x, y).getStyle().setBorderWidth(Unit.Pixel(3));
	}

	public void headerBarAndCommandButton(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		 
		// reload the new file
		try {
			super.reloadfile(gridweb,request,"ShowHeaderBar.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean noScrollBars =   request.getParameter("noScrollBars").equals("checked");
		boolean showHeaderBar = Boolean.parseBoolean(request.getParameter("showHeaderBar"));
		boolean showSubmitButton = Boolean.parseBoolean(request.getParameter("showSubmitButton"));
		boolean showSaveButton = Boolean.parseBoolean(request.getParameter("showSaveButton"));
		boolean showUndoButton = Boolean.parseBoolean(request.getParameter("showUndoButton"));

		gridweb.setShowHeaderBar(showHeaderBar);
		gridweb.setShowSubmitButton(showSubmitButton);
		gridweb.setShowSaveButton(showSaveButton);
		gridweb.setShowUndoButton(showUndoButton);
		gridweb.setNoScroll(noScrollBars);
		if(!showSaveButton) {
			 CustomCommandButton ccb_button = new CustomCommandButton();
	            ccb_button.setWidth(Unit.Pixel(20));
	            ccb_button.setImageUrl( "images/album.gif");
	            ccb_button.setCommandType(CustomCommandButtonType.COMMAND_BUTTON);
	            ccb_button.setCommand("CommandTest");
	            ccb_button.setToolTip( "click me!");
	            gridweb.getCustomCommandButtons().add(ccb_button);
	            gridweb.CustomCommand=new MyCommandEventHandler();
		}else {
			 gridweb.getCustomCommandButtons().clear();
		}
	}

	public void validation(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		 
		// reload the new file
		try {
			super.reloadfile(gridweb,request,"input.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean validation = Boolean.parseBoolean(request.getParameter("validation"));
		gridweb.setForceValidation(validation);
		if (!validation) { // validation is disabled
			return;
		}
		GridWorksheetCollection gridWorksheetCollection = gridweb.getWorkSheets();
		GridWorksheet gridWorksheet = gridWorksheetCollection.get(0);
		GridValidationCollection gridValidationCollection = gridWorksheet.getValidations();

		GridValidation C5 = gridValidationCollection.add("C5");
		C5.setOperator(OperatorType.BETWEEN);
		C5.setValidationType(GridValidationType.CUSTOM_EXPRESSION);
		C5.setRegEx("\\d{6}");

		GridValidation C6 = gridValidationCollection.add("C6");
		C6.setOperator(OperatorType.NONE);
		C6.setValidationType(GridValidationType.DECIMAL);

		GridValidation C7 = gridValidationCollection.add("C7");
		C7.setOperator(OperatorType.NONE);
		C7.setValidationType(GridValidationType.WHOLE_NUMBER);

		GridValidation C8 = gridValidationCollection.add("C8");
		C8.setOperator(OperatorType.NONE);
		C8.setValidationType(GridValidationType.DATE);

		GridValidation C9 = gridValidationCollection.add("C9");
		C9.setOperator(OperatorType.BETWEEN);
		C9.setValidationType(GridValidationType.DATE_TIME);

		GridValidation C10 = gridValidationCollection.add("C10");
		C10.setOperator(OperatorType.BETWEEN);
		C10.setValidationType(GridValidationType.LIST);
		ArrayList<String> C10List = new ArrayList<String>();
		C10List.add("Fortran");
		C10List.add("Pascal");
		C10List.add("C++");
		C10List.add("Visual Basic");
		C10List.add("Java");
		C10List.add("C#");
		C10.setValueList(C10List);

		GridValidation C11 = gridValidationCollection.add("C11");
		C11.setOperator(OperatorType.BETWEEN);
		C11.setValidationType(GridValidationType.DROP_DOWN_LIST);
		ArrayList<String> C11List = new ArrayList<String>();
		C11List.add("Bachelor");
		C11List.add("Master");
		C11List.add("Doctor");
		C11.setValueList(C11List);

		GridValidation C12 = gridValidationCollection.add("C12");
		C12.setOperator(OperatorType.BETWEEN);
		C12.setValidationType(GridValidationType.FREE_LIST);
		ArrayList<String> C12List = new ArrayList<String>();
		C12List.add("US");
		C12List.add("Britain");
		C12List.add("France");
		C12.setValueList(C12List);

		GridValidation C13 = gridValidationCollection.add("C13");
		C13.setOperator(OperatorType.BETWEEN);
		C13.setValidationType(GridValidationType.CUSTOM_FUNCTION);
		C13.setClientValidationFunction("myvalidation1");

		GridValidation C14 = gridValidationCollection.add("C14");
		C14.setOperator(OperatorType.BETWEEN);
		C14.setValidationType(GridValidationType.CHECK_BOX);
		// style
		GridValidation gv = gridValidationCollection.add(new GridCellArea(14,2, 15, 2));
        gv.setValidationType(GridValidationType.CUSTOM_SERVER_FUNCTION);
        gv.ServerValidation = new myservervali();
      
        gv.setClientValidationFunction("ValidationErrorClientFunctionCallback");
        gv.setErrorMessage("error message is here"); 
        gv.setErrorTitle("this is error title");  
	}

	public void autoFilter(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {
		this.reload(gridweb,request, response);
		// reload the new file
		try {
			super.reloadfile(gridweb,request,"autofilter.xls");
		} catch (Exception e) {
			e.printStackTrace();
		}

		GridWorksheet gridWorksheet = gridweb.getWorkSheets().get(0);
		gridWorksheet.removeAutoFilter();
		gridWorksheet.addAutoFilter(4, 0, 60);
		// gridWorksheet.FilterString(5, "ccffff,ddd");
		// gridWorksheet.FilterString(7, "dddddd");
		// gridWorksheet.AddCustomFilter(9, "cell5=ccffff,ddd;cell8=cccc");
		gridWorksheet.refreshFilter();

	}

	public void customFilter(GridWebBean gridweb,HttpServletRequest request, HttpServletResponse response) {

	}
}
