package com.rahul.controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;

import com.rahul.dto.InsurancePolicyDTO;
import com.rahul.service.InsurancePolicyService;
import com.rahul.service.InsurancePolicyServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/controller", loadOnStartup = 1)
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InsurancePolicyService service;

	@Override
	public void init() throws ServletException {
		service = new InsurancePolicyServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo = 0;
		int pageSize = 0;
		List<InsurancePolicyDTO> listDto = null;
		String targetPage = null;
		RequestDispatcher rd = null;
		long pagesCount = 0L;
		HttpSession session = null;

		String value = request.getParameter("s1");
		session = request.getSession(true);

		if (value.equalsIgnoreCase("generatereport")) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			pageNo = 1;
			if (session != null)
				session.setAttribute("pageSize", pageSize);
		} else {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			if (session != null)
				pageSize = (int) session.getAttribute("pageSize");
		}

		try {
			pagesCount = service.fetchPageCount(pageSize);
			listDto = service.fetchPageData(pageNo, pageNo);

			request.setAttribute("policiesList", listDto);
			request.setAttribute("pagesCount", pagesCount);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageNo", pageNo);
			targetPage = "./report.jsp";
		} catch (HibernateException e) {
			e.printStackTrace();
			targetPage = "./error.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			targetPage = "./error.jsp";
		}
		rd = request.getRequestDispatcher(targetPage);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {
		service = null;
	}

}
