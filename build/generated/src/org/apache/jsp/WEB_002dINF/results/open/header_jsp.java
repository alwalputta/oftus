package org.apache.jsp.WEB_002dINF.results.open;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<div class=\"dockingBarTop\">\n");
      out.write("    <div class=\"top-column-element-left\">\n");
      out.write("        <div class=\"company-logo\">\n");
      out.write("            <a href=\"http://www.oftus.com\">\n");
      out.write("                <img id=\"logo\" src=\"images/oftus2.jpg\"/>\n");
      out.write("            </a>\n");
      out.write("            <!--Oftus<sup>&reg;</sup>-->\n");
      out.write("            <!--div class=\"company-logo-punchline\">\n");
      out.write("                Your One Link World.\n");
      out.write("            </div-->\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"top-column-element-middle\">\n");
      out.write("        <!--        Buy Samsung TV Today!-->\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"top-column-element-right\">\n");
      out.write("        <a href=\"edit_profile\">\n");
      out.write("            <img class =\"photo-box\" src=\"<s:url action='ImageServlet'/>\" alt=\"Welcome, <s:property value=\"%{#session.user.firstName}\"/> <s:property value=\"%{#session.user.lastName}\"/>\"/>\n");
      out.write("        </a>\n");
      out.write("        <ul id=\"menu\">\n");
      out.write("            <li>\n");
      out.write("            <s:if test=\"%{#session.user.firstName} == null\">\n");
      out.write("                <a href=\"index\">Login</a>\n");
      out.write("            </s:if>\n");
      out.write("            <s:else>\n");
      out.write("                <a href=\"index\">\n");
      out.write("                    Hi, <s:property value=\"%{#session.user.firstName}\"/>\n");
      out.write("                </a>\n");
      out.write("            </s:else>\n");
      out.write("            <ul>\n");
      out.write("                <li><a href=\"welcome\">Welcome to Oftus!</a></li>\n");
      out.write("                <li><a href=\"edit_profile\">Manage Profile</a></li>\n");
      out.write("                <li><a href=\"edit_preferences\">Manage Preferences</a></li>\n");
      out.write("                <li><a href=\"refer_friends\">Refer Friends</a></li>\n");
      out.write("                <li><a href=\"http://localhost:4848\">Server Admin</a></li>\n");
      out.write("            </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"user_options\">Options</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"list_categories\">Manage Categories</a></li>\n");
      out.write("                    <li><a href=\"list_bookmarks\">Manage Bookmarks</a></li>\n");
      out.write("                    <li><a href=\"import_bookmarks_page\">Import Bookmarks</a></li>\n");
      out.write("                    <li><a href=\"industry_trends\">Industry Trends</a></li>\n");
      out.write("                    <li><a href=\"personal_reports\">Personal Reports</a></li>\n");
      out.write("                    <li><a href=\"our_recommendations\">Recommendations</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"user_help\">Help</a> \n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"release_notes\">Release Notes</a></li>\n");
      out.write("                    <li><a href=\"quick_guide\">Quick Guide</a></li>\n");
      out.write("                    <li><a href=\"user_guide\">User Guide</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("            <li><a href=\"logout\">Logout</a>\n");
      out.write("                <ul>\n");
      out.write("                    <li><a href=\"logout\">Logout me</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("        <div class=\"search-box\">\n");
      out.write("            <s:form action=\"searchForm\">\n");
      out.write("                <input type=\"text\" id=\"search-input\" name=\"search-input\"/>\n");
      out.write("                <input type=\"button\" id=\"search-button\" name=\"search-button\"/>\n");
      out.write("            </s:form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
