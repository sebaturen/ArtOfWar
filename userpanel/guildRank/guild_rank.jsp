<%@include file="../../includes/globalObject.jsp" %>
<%
if(user.getGuildRank() != 0 && user.getGuildRank() != 1)
{//Validate user is Guild Lider or Officer %><%@ page import ="java.net.URLEncoder" %><%
    response.sendRedirect("../../login.jsp?rdir="+URLEncoder.encode("userpanel/guildRank/guild_rank.jsp", "UTF-8"));
}
else
{%>
<%@ page import ="com.blizzardPanel.gameObject.guild.Rank" %>
<%@ page import ="com.blizzardPanel.gameObject.characters.Member" %>
<%@ page import = "java.util.Map" %>
<jsp:useBean id="ranks" class="com.blizzardPanel.viewController.GuildRanks" scope="request"/>
<%
    if(request.getParameter("save_apply") != null && request.getParameter("save_apply").equals("true"))
    {
        Map<String, String[]> parameters = request.getParameterMap();
        for(String parameter : parameters.keySet())
        {
            if(parameter.toLowerCase().startsWith("title_")) 
            {
                String[] values = parameters.get(parameter);
                //Save a new title>
                ranks.setRankInfo(parameter.substring(6), values[0]);
            }
        }
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title><%= guild_info.getName() %> - Guild Rank Setting panel</title>
        <%@include file="../../includes/header.jsp" %>
    </head>
    <body>
        <%@include file="../../includes/menu.jsp" %>
        <div class="container fill">
            <p>List of ranks detected</p>
            <form id='formRanks' method="post">
            <% for(Rank r : ranks.getRanks()){ %>
                <div class="form-group">
                    <label><%= r.getTitle() %> (<%= r.getId() %>)</label>
                    <input class="form-control" type="text" value="<%= r.getTitle() %>" name="title_<%= r.getId() %>" <%= (r.getId() == 0 || r.getId() == 1)? "disabled":"" %>/>
                    <% for (Member m : ranks.getMemberByRank(r.getId())) {
                        String clasName = m.getMemberClass().getEnName().toLowerCase();
                    %>
                        <span class="character-<%= clasName %> mem-name"><%= m.getName() %></span>,
                    <%} //end foreach members %>
                </div>
          <%}//end foreach ranks%>
                <input type="hidden" value="true" name="save_apply" />
                <button type="submit" class="btn btn-primary">Save change</button>
            </form>
        </div>
    </body>
</html>
<%}%>