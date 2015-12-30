package com.hungnv132.web.controller.export;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.hungnv132.core.dao.UserDAO;
import com.hungnv132.core.domain.User;


@Controller
public class ExportTestController {

	@Inject
	ServletContext servletContext;

	@Inject
	ApplicationContext appContext;

	@Inject
	UserDAO userDAO;

	@RequestMapping(value = "/export/user", method = RequestMethod.GET)
	// @ResponseBody
	public void userExport(HttpServletResponse response) throws JRException, IOException {
		 InputStream jasperStream =
		 servletContext.getResourceAsStream("/WEB-INF/exports/test.jasper");
		
		 
		 Map<String,Object> params = new HashMap<String, Object>();
		 params.put("reportTitle", "Danh sách người dùng");
		 List<User> listUser = userDAO.findAll();
		
		 JRBeanCollectionDataSource dataSource = new
		 JRBeanCollectionDataSource(listUser);
		 JasperReport jasperReport = (JasperReport)
		 JRLoader.loadObject(jasperStream);
		 JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
		 params, dataSource);
		
		 response.setContentType("application/x-pdf");
		 response.setHeader("Content-disposition",
		 "inline; filename=user_list.pdf");
		
		 final OutputStream outStream = response.getOutputStream();
		 JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		 

	}

	@RequestMapping(value = "/test/viewpdf", method = RequestMethod.GET)
	public ModelAndView viewpdf(ModelAndView modelAndView) throws JRException, IOException {
		List<User> listUser = userDAO.findAll();
		// pdfView.setReportDataKey("listUser");

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listUser);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reportTitle", "Danh sách người dùng");
		params.put("datasource", dataSource);

		JasperReportsPdfView pdfView = new JasperReportsPdfView();
		pdfView.setUrl("/WEB-INF/exports/test.jasper");
		pdfView.setApplicationContext(appContext);
		return new ModelAndView(pdfView, params);

	}

	@RequestMapping(value = "/test/export", method = RequestMethod.GET)
	@ResponseBody
	public void exportTest(HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = servletContext.getResourceAsStream("/WEB-INF/exports/test.jasper");

		Map<String, Object> params = new HashMap<String, Object>();

		// JRBeanCollectionDataSource dataSource = new
		// JRBeanCollectionDataSource();
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}
}
