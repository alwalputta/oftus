
<div class="dockingBarTop">
    <div class="top-column-element-left">
        <div class="company-logo">
            <!--img id="logo" src="images/oftus2.jpg"/-->
            <img id="logo" src="images/oftus.gif"/>
            <!--OFTUS<sup>&reg;</sup>-->
            <!--div class="company-logo-punchline">
                Your One Link World.
            </div-->
        </div>
    </div>
    <div class="top-column-element-middle">
<!--        Buy Samsung TV Today!-->
    </div>

    <div class="top-column-element-right">
        <img class ="photo-box" src="<s:url action='ImageServlet'/>" alt="Welcome, <s:property value="%{#session.user.firstName}"/> <s:property value="%{#session.user.lastName}"/>"/>
        <ul id="menu">
            <li>
            <s:if test="%{#session.user.firstName} == null">
                <a href="login_form">Login</a>
            </s:if>
            <s:else>
                <a href="login">
                    Hi, <s:property value="%{#session.user.firstName}"/>
                </a>
            </s:else>
            <ul>
                <li><a href="welcome">Welcome!</a></li>
                <li><a href="edit_profile">Manage Profile</a></li>
                <li><a href="edit_preferences">Manage Preferences</a></li>
                <li><a href="refer_friends">Refer Friends</a></li>
                <li><a href="http://localhost:4848">Server Admin</a></li>
            </ul>
            </li>
            <li><a href="user_options">Options</a>
                <ul>
                    <li><a href="list_categories">Manage Categories</a></li>
                    <li><a href="list_bookmarks">Manage Bookmarks</a></li>
                    <li><a href="import_bookmarks_page">Import Bookmarks</a></li>
                    <li><a href="industry_trends">Industry Trends</a></li>
                    <li><a href="personal_reports">Personal Reports</a></li>
                    <li><a href="our_recommendations">Recommendations</a></li>
                </ul>
            </li>
            <li><a href="user_help">Help</a> 
                <ul>
                    <li><a href="release_notes">Release Notes</a></li>
                    <li><a href="quick_guide">Quick Guide</a></li>
                    <li><a href="user_guide">User Guide</a></li>
                </ul>
            </li>
            <li><a href="logout">Logout</a>
                <ul>
                    <li><a href="logout">Logout me</a></li>
                </ul>
            </li>
        </ul>
        <div class="search-box">
            <s:form action="searchForm">
                <input type="text" id="search-input" name="search-input"/>
                <input type="button" id="search-button" name="search-button"/>
            </s:form>
        </div>
    </div>
</div>
