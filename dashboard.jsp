<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.goldhuman.auth.AuthFilter"%>
<%@ page import="org.apache.commons.logging.LogFactory"%>
<%@ page import="com.goldhuman.util.LocaleUtil" %>

<%
// Log access to this page
LogFactory.getLog("dashboard.jsp").info(
    "Game dashboard accessed by operator=" + AuthFilter.getRemoteUser(session));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title><%= LocaleUtil.getMessage(request,"role_manage_manage") %> - Dashboard</title>
    <link href="include/style.css" rel="stylesheet" type="text/css">
    <style>
        .card-container {
            display: flex;
            flex-wrap: wrap;
            margin: 10px 0;
        }
        .card {
            width: 30%;
            margin: 5px;
            padding: 10px;
            border: 1px solid #ddd;
            background-color: #f9f9f9;
        }
        .card h3 {
            margin-top: 0;
            color: #9E0B0E;
            border-bottom: 1px solid #ddd;
            padding-bottom: 5px;
        }
        .card p {
            min-height: 40px;
        }
        .section-header {
            background-color: #6699CC;
            color: white;
            padding: 5px 10px;
            font-weight: bold;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <%@include file="include/header.jsp"%>
    
    <table width="100%" height="514" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
        <tr>
            <td>
                <table width="90%" align="center" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="table_title" height="30">
                            <%= LocaleUtil.getMessage(request,"role_manage_manage") %> - Main Dashboard
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <!-- Character Management Section -->
                            <div class="section-header">Character Management</div>
                            
                            <div class="card-container">
                                <div class="card">
                                    <h3>View/Edit Character</h3>
                                    <p>View and modify character attributes such as level, stats, money.</p>
                                    <a href="role/modrole.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Modify XML Data</h3>
                                    <p>Advanced editing of character data in XML format.</p>
                                    <a href="role/modrolexml.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Specific Attribute Editor</h3>
                                    <p>Edit individual character attributes like level, exp, money, PKvalue.</p>
                                    <a href="role/modifyroledata.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Character List</h3>
                                    <p>View all characters for a specific user account.</p>
                                    <a href="role/rolelist.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Character Count</h3>
                                    <p>Check how many characters a user has created.</p>
                                    <a href="role/userrolecount.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Check Character Status</h3>
                                    <p>View login status of a character (online/offline).</p>
                                    <a href="role/rolelogstatus.jsp">Access</a>
                                </div>
                            </div>
                            
                            <!-- Character Operations Section -->
                            <div class="section-header">Character Operations</div>
                            
                            <div class="card-container">
                                <div class="card">
                                    <h3>Rename Character</h3>
                                    <p>Change a character's name.</p>
                                    <a href="role/renamerole.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Delete Character</h3>
                                    <p>Delete a character from the game.</p>
                                    <a href="role/deleterole.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Ban Character</h3>
                                    <p>Temporarily forbid a character from playing.</p>
                                    <a href="role/forbidrole.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Check Name Availability</h3>
                                    <p>Check if a character name is already taken.</p>
                                    <a href="role/rolenameexists.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Present Items</h3>
                                    <p>Gift items to characters.</p>
                                    <a href="role/presentGoods.jsp">Access</a>
                                </div>
                            </div>
                            
                            <!-- Economy Management Section -->
                            <div class="section-header">Economy Management</div>
                            
                            <div class="card-container">
                                <div class="card">
                                    <h3>Clear All Money</h3>
                                    <p>Reset all currency for a character.</p>
                                    <a href="role/clearallmoney.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>View Bonus</h3>
                                    <p>Check bonus withdraw value for a character.</p>
                                    <a href="role/getGRoleBase2.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Modify Bonus</h3>
                                    <p>Edit the bonus withdraw value for a character.</p>
                                    <a href="role/putGRoleBase2.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Disable Autolock</h3>
                                    <p>Prevent account from auto-locking due to inactivity.</p>
                                    <a href="role/disableautolock.jsp">Access</a>
                                </div>
                            </div>
                            
                            <!-- Backup Management Section -->
                            <div class="section-header">Backup Management</div>
                            
                            <div class="card-container">
                                <div class="card">
                                    <h3>List Backups</h3>
                                    <p>View all available character backups.</p>
                                    <a href="role/listBackups.jsp">Access</a>
                                </div>
                                
                                <div class="card">
                                    <h3>Get Backup Role</h3>
                                    <p>Retrieve a backed up character.</p>
                                    <a href="role/getBackupRole.jsp">Access</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    
    <%@include file="include/foot.jsp"%>
</body>
</html>