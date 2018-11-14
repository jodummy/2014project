package ya.rain.bow.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessLogFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		String remoteAddr = StringUtils.defaultString(request.getRemoteAddr(), "-");
		// String uri = StringUtils.defaultString(request.getRequestURI(),"");
		String url = (request.getRequestURL() == null) ? "" : request.getRequestURL().toString();
		String queryString = StringUtils.defaultIfEmpty(request.getQueryString(), "");
		String refer = StringUtils.defaultString(request.getHeader("Refer"), "-");
		String agent = StringUtils.defaultString(request.getHeader("User-Agent"), "-");
		String fullUrl = url;

		fullUrl += StringUtils.isNotEmpty(queryString) ? "?" + queryString : queryString;

		StringBuffer result = new StringBuffer();
		result.append(":").append(remoteAddr)
			.append(":").append(fullUrl)
			.append(":").append(refer)
			.append(":").append(agent);
		logger.info("LOG FILTER" + result.toString());
		chain.doFilter(request, resp);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
