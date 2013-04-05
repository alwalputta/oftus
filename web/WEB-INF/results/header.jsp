<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

        <title>HomeStuff - Development</title>

        <link rel="shortcut icon" type="image/x-icon" class="header-icon" href="images/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="css/myapp.css"/>
        <link rel="stylesheet" type="text/css" media="all" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/sunny/jquery-ui.css"/>

        <!--script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script-->
        <script type="text/javascript" src="js/jquery.highlight-3.js"></script>
        <script type="text/javascript" src="js/constants.js"></script>
        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <script type="text/javascript">
            
            /*            $('#c2').data ({colId:"c2", position:2, title: "Column 2"});
            $('#c2r1').data ({linkId:"c2r1", position:1, hiperLink:"www.yahoo.com", title: "Yahoo!", comments:"my favotite for news"});
             */
           
            function loadData () {
                //alert ('aaaaa');
                //columns
                $('#c1').data({colId:"1", title:"Builder"});
                $('#c2').data({colId:"2", title:"Business"});
                $('#c3').data({colId:"3", title:"Career"});
                $('#c4').data({colId:"4", title:"Family"});
                $('#c5').data({colId:"5", title:"General"});
                $('#c6').data({colId:"6", title:"Home"});
                $('#c7').data({colId:"7", title:"News"});
                $('#c8').data({colId:"8", title:"Personal"});
                $('#c9').data({colId:"9", title:"Shopping"});
                $('#c10').data({colId:"10", title:"Technical"});
                $('#c11').data({colId:"11", title:"Utilities"});

                //$('#c2r1').data({linkId:"c2r1", hiperLink:"http://www.yahoo.com", title: "Yahoo!", comments:"my favotite for news"});
                //rows
                $('#c1r1').data({linkId:"c1r1", title:"Braddock And Logan Homes", hiperLink:"http://www.braddockandloganhomes.com/", description:"test link"});
                $('#c1r2').data({linkId:"c1r2", title:"Brookfield Homes", hiperLink:"http://www.brookfieldhomes.com/"});
                $('#c1r3').data({linkId:"c1r3", title:"Centex", hiperLink:"http://www.centex.com/"});
                $('#c1r4').data({linkId:"c1r4", title:"Greencherry Commons", hiperLink:"http://www.greencherrycommons.com/"});
                $('#c1r5').data({linkId:"c1r5", title:"John Laing", hiperLink:"http://www.JohnLaingHomes.com/"});
                $('#c1r6').data({linkId:"c1r6", title:"KB Home", hiperLink:"http://www.kbhomes.com/"});
                $('#c1r7').data({linkId:"c1r7", title:"Lennar", hiperLink:"http://www.lennar.com/"});
                $('#c1r8').data({linkId:"c1r8", title:"Olson Homes", hiperLink:"http://www.olsonhomes.com/"});
                $('#c1r9').data({linkId:"c1r9", title:"Pulte", hiperLink:"http://www.pulte.com/"});
                $('#c1r10').data({linkId:"c1r10", title:"Richmond American", hiperLink:"http://www.richmondamerican.com/"});
                $('#c1r11').data({linkId:"c1r11", title:"Robson Homes", hiperLink:"http://www.robsonhomes.com/"});
                $('#c1r12').data({linkId:"c1r12", title:"Shapell Homes", hiperLink:"http://www.shapellnc.com/"});
                $('#c1r13').data({linkId:"c1r13", title:"Standard Pacific Homes", hiperLink:"http://www.standardpacifichomes.com/"});
                $('#c1r14').data({linkId:"c1r14", title:"Summerhill Homes", hiperLink:"http://www.summerhillhomes.com/"});
                $('#c1r15').data({linkId:"c1r15", title:"Taylor Morrison", hiperLink:"http://www.taylormorrison.com/"});
                $('#c1r16').data({linkId:"c1r16", title:"Toll Brothers", hiperLink:"http://www.tollbrothers.com/"});
                $('#c2r17').data({linkId:"c2r17", title:"AJPM", hiperLink:"http://www.ajpm.com/"});
                $('#c2r18').data({linkId:"c2r18", title:"APMEX", hiperLink:"http://www.apmex.com/"});
                $('#c2r19').data({linkId:"c2r19", title:"Barrons", hiperLink:"http://www.barrons.com/"});
                $('#c2r20').data({linkId:"c2r20", title:"BusinessStandard", hiperLink:"http://www.businessstandard.com/"});
                $('#c2r21').data({linkId:"c2r21", title:"Business Week", hiperLink:"http://www.businessweek.com/"});
                $('#c2r22').data({linkId:"c2r22", title:"CNN Money", hiperLink:"http://money.cnn.com/"});
                $('#c2r23').data({linkId:"c2r23", title:"Economy.com", hiperLink:"http://www.economy.com/"});
                $('#c2r24').data({linkId:"c2r24", title:"Economic Times", hiperLink:"http://economictimes.indiatimes.com/"});
                $('#c2r25').data({linkId:"c2r25", title:"Financial Times", hiperLink:"http://www.ft.com/"});
                $('#c2r26').data({linkId:"c2r26", title:"Forbes", hiperLink:"http://www.forbes.com/"});
                $('#c2r27').data({linkId:"c2r27", title:"Forrester", hiperLink:"http://www.forrester.com/"});
                $('#c2r28').data({linkId:"c2r28", title:"Fortune", hiperLink:"http://www.fortune.com/"});
                $('#c2r29').data({linkId:"c2r29", title:"Gartner", hiperLink:"http://www.gartner.com/"});
                $('#c2r30').data({linkId:"c2r30", title:"Google Finance", hiperLink:"http://finance.google.com/"});
                $('#c2r31').data({linkId:"c2r31", title:"Hindu Business", hiperLink:"http://www.thehindubusinessline.com/"});
                $('#c2r32').data({linkId:"c2r32", title:"Hoovers", hiperLink:"http://www.hoovers.com/"});
                $('#c2r33').data({linkId:"c2r33", title:"Investopedia", hiperLink:"http://www.investopedia.com/"});
                $('#c2r34').data({linkId:"c2r34", title:"Kitco", hiperLink:"http://www.kitco.com/"});
                $('#c2r35').data({linkId:"c2r35", title:"Market Watch", hiperLink:"http://www.marketwatch.com/"});
                $('#c2r36').data({linkId:"c2r36", title:"Money Central", hiperLink:"http://www.moneycentral.com/"});
                $('#c2r37').data({linkId:"c2r37", title:"Money Control", hiperLink:"http://www.moneycontrol.com/"});
                $('#c2r38').data({linkId:"c2r38", title:"Moneypore", hiperLink:"http://www.moneypore.com/"});
                $('#c2r39').data({linkId:"c2r39", title:"Rediff Money", hiperLink:"http://money.rediff.com/"});
                $('#c2r40').data({linkId:"c2r40", title:"Reuters", hiperLink:"http://www.reuters.com/"});
                $('#c2r41').data({linkId:"c2r41", title:"Salary", hiperLink:"http://www.salary.com/"});
                $('#c2r42').data({linkId:"c2r42", title:"Seeking Alpha", hiperLink:"http://www.seekingalpha.com/"});
                $('#c2r43').data({linkId:"c2r43", title:"The Street", hiperLink:"http://www.thestreet.com/"});
                $('#c2r44').data({linkId:"c2r44", title:"Wallstreet", hiperLink:"http://online.wsj.com/public/us"});
                $('#c2r45').data({linkId:"c2r45", title:"Yahoo!Quote", hiperLink:"http://finance.yahoo.com/"});
                $('#c3r46').data({linkId:"c3r46", title:"BPMI", hiperLink:"http://www.bpmi.org/"});
                $('#c3r47').data({linkId:"c3r47", title:"Biospace", hiperLink:"http://www.biospace.com/"});
                $('#c3r48').data({linkId:"c3r48", title:"Burton Group", hiperLink:"http://www.burtongroup.com/"});
                $('#c3r49').data({linkId:"c3r49", title:"Career Builder", hiperLink:"http://www.careerbuilder.com/"});
                $('#c3r50').data({linkId:"c3r50", title:"CIO", hiperLink:"http://www.cio.com/"});
                $('#c3r51').data({linkId:"c3r51", title:"Computer World", hiperLink:"http://www.computerworld.com/"});
                $('#c3r52').data({linkId:"c3r52", title:"Dataquest", hiperLink:"http://dqindia.ciol.com/"});
                $('#c3r53').data({linkId:"c3r53", title:"Dice", hiperLink:"http://www.dice.com/"});
                $('#c3r54').data({linkId:"c3r54", title:"Elance", hiperLink:"http://www.elance.com/"});
                $('#c3r55').data({linkId:"c3r55", title:"eWeek", hiperLink:"http://www.eweek.com/"});
                $('#c3r56').data({linkId:"c3r56", title:"Information Week", hiperLink:"http://www.informationweek.com/"});
                $('#c3r57').data({linkId:"c3r57", title:"Infotoday", hiperLink:"http://www.infotoday.com/"});
                $('#c3r58').data({linkId:"c3r58", title:"Infoworld", hiperLink:"http://www.infoworld.com/"});
                $('#c3r59').data({linkId:"c3r59", title:"Job.com", hiperLink:"http://www.job.com/"});
                $('#c3r60').data({linkId:"c3r60", title:"Meetup", hiperLink:"http://www.meetup.com/"});
                $('#c3r61').data({linkId:"c3r61", title:"Monster", hiperLink:"http://www.monster.com/"});
                $('#c3r62').data({linkId:"c3r62", title:"Net Temps", hiperLink:"http://www.nettemps.com/"});
                $('#c3r63').data({linkId:"c3r63", title:"Oracle Magazine", hiperLink:"http://www.oracle.com/oramag/index.html"});
                $('#c3r64').data({linkId:"c3r64", title:"PCMagazine", hiperLink:"http://www.pcmag.com/"});
                $('#c3r65').data({linkId:"c3r65", title:"PCWorld", hiperLink:"http://www.pcworld.com/"});
                $('#c3r66').data({linkId:"c3r66", title:"Tradepub", hiperLink:"http://www.tradepub.com/"});
                $('#c3r67').data({linkId:"c3r67", title:"UML", hiperLink:"http://www.omg.org/UML"});
                $('#c3r68').data({linkId:"c3r68", title:"WVDL", hiperLink:"http://www.wvdl.com/"});
                $('#c3r69').data({linkId:"c3r69", title:"XML", hiperLink:"http://www.xml.com/"});
                $('#c4r70').data({linkId:"c4r70", title:"Andhra Vilas", hiperLink:"http://www.andhravilas.com/"});
                $('#c4r71').data({linkId:"c4r71", title:"Chimata Music", hiperLink:"http://www.chimatamusic.com/"});
                $('#c4r72').data({linkId:"c4r72", title:"CineLux", hiperLink:"http://www.cineluxtheatres.com/"});
                $('#c4r73').data({linkId:"c4r73", title:"Concord Temple", hiperLink:"https://www.shivamurugantemple.org/"});
                $('#c4r74').data({linkId:"c4r74", title:"Evite", hiperLink:"http://www.evite.com/"});
                $('#c4r75').data({linkId:"c4r75", title:"Fremont Temple", hiperLink:"http://fremonttemple.org/"});
                $('#c4r76').data({linkId:"c4r76", title:"Great Andhra", hiperLink:"http://www.greatandhra.com/"});
                $('#c4r77').data({linkId:"c4r77", title:"Idlebrain", hiperLink:"http://www.idlebrain.com/"});
                $('#c4r78').data({linkId:"c4r78", title:"Indiaglitz", hiperLink:"http://www.indiaglitz.com/"});
                $('#c4r79').data({linkId:"c4r79", title:"LivermoreTemple", hiperLink:"http://www.livermoretemple.org/"});
                $('#c4r80').data({linkId:"c4r80", title:"MSN Maps", hiperLink:"http://virtualearth.msn.com/"});
                $('#c4r81').data({linkId:"c4r81", title:"Riella Farms", hiperLink:"http://riellafarms.com/"});
                $('#c4r82').data({linkId:"c4r82", title:"San Jose Temple", hiperLink:"http://www.vvgc.org/"});
                $('#c4r83').data({linkId:"c4r83", title:"Sunlight Travel", hiperLink:"http://www.sunlighttravel.com/"});
                $('#c4r84').data({linkId:"c4r84", title:"SunnyvaleTemple", hiperLink:"http://www.sunnyvaletemple.org/"});
                $('#c4r85').data({linkId:"c4r85", title:"Suntrips", hiperLink:"http://www.suntrips.com/"});
                $('#c4r86').data({linkId:"c4r86", title:"Teluguone", hiperLink:"http://www.teluguone.com/"});
                $('#c4r87').data({linkId:"c4r87", title:"Telugu Pictures", hiperLink:"http://www.telugupictures.com/"});
                $('#c4r88').data({linkId:"c4r88", title:"Tupaki", hiperLink:"http://www.tupaki.com/"});
                $('#c4r89').data({linkId:"c4r89", title:"Wikia", hiperLink:"http://www.wikia.com/"});
                $('#c4r90').data({linkId:"c4r90", title:"Wikimapia", hiperLink:"http://www.wikimapia.org/"});
                $('#c4r91').data({linkId:"c4r91", title:"Wikimedia", hiperLink:"http://www.wikimedia.org/"});
                $('#c4r92').data({linkId:"c4r92", title:"Wikipedia", hiperLink:"http://www.wikipedia.com/"});
                $('#c5r93').data({linkId:"c5r93", title:"About", hiperLink:"http://www.about.com/"});
                $('#c5r94').data({linkId:"c5r94", title:"AILA", hiperLink:"http://www.aila.org/"});
                $('#c5r95').data({linkId:"c5r95", title:"Ask", hiperLink:"http://www.ask.com/"});
                $('#c5r96').data({linkId:"c5r96", title:"Annex Ventures", hiperLink:"http://www.annexventures.com/"});
                $('#c5r97').data({linkId:"c5r97", title:"CricInfo", hiperLink:"http://www.cricinfo.com/"});
                $('#c5r98').data({linkId:"c5r98", title:"Infobrain", hiperLink:"http://www.infobraintech.com/"});
                $('#c5r99').data({linkId:"c5r99", title:"Fare Compare", hiperLink:"http://www.farecompare.com/"});
                $('#c5r100').data({linkId:"c5r100", title:"Google", hiperLink:"http://www.google.com/"});
                $('#c5r101').data({linkId:"c5r101", title:"Hollywood", hiperLink:"http://www.hollywoodvideo.com/"});
                $('#c5r102').data({linkId:"c5r102", title:"ILW", hiperLink:"http://www.ilw.org/"});
                $('#c5r103').data({linkId:"c5r103", title:"ImmigrationPortal", hiperLink:"http://www.immigrationportal.com/"});
                $('#c5r104').data({linkId:"c5r104", title:"Jetblue", hiperLink:"http://www.jetblue.com/"});
                $('#c5r105').data({linkId:"c5r105", title:"Linkz", hiperLink:"http://www.linkz.com/"});
                $('#c5r106').data({linkId:"c5r106", title:"MSN", hiperLink:"http://www.msn.com/"});
                $('#c5r107').data({linkId:"c5r107", title:"NASA", hiperLink:"http://www.nasa.gov/"});
                $('#c5r108').data({linkId:"c5r108", title:"Path2USA", hiperLink:"http://www.path2usa.com/"});
                $('#c5r109').data({linkId:"c5r109", title:"Register", hiperLink:"http://www.register.com/"});
                $('#c5r110').data({linkId:"c5r110", title:"Savethis", hiperLink:"http://www.savethis.com/"});
                $('#c5r111').data({linkId:"c5r111", title:"SutiSoft", hiperLink:"http://www.sutisoft.com/"});
                $('#c5r112').data({linkId:"c5r112", title:"TTVISA", hiperLink:"http://www.ttsvisas.com/"});
                $('#c5r113').data({linkId:"c5r113", title:"USCIS", hiperLink:"https://egov.uscis.gov/cris/jsps/login.jsp"});
                $('#c5r114').data({linkId:"c5r114", title:"Yahoo!", hiperLink:"http://www.yahoo.com/"});
                $('#c5r115').data({linkId:"c5r115", title:"YouTube", hiperLink:"http://www.youtube.com/"});
                $('#c5r116').data({linkId:"c5r116", title:"Zaba Search", hiperLink:"http://zabasearch.com/"});
                $('#c6r117').data({linkId:"c6r117", title:"Alliance Title", hiperLink:"http://www.alliancetitleco.com/"});
                $('#c6r118').data({linkId:"c6r118", title:"Bayarea Realtor", hiperLink:"http://www.realtor.com/california/nbselbay.asp?st=ca&amp;poe=realtor"});
                $('#c6r119').data({linkId:"c6r119", title:"CA DOE", hiperLink:"http://www.cde.ca.gov/ta/ac/ap/"});
                $('#c6r120').data({linkId:"c6r120", title:"CA Realtor", hiperLink:"http://ca.realtor.com/"});
                $('#c6r121').data({linkId:"c6r121", title:"Coldwell Banker", hiperLink:"http://www.coldwellbanker.com/"});
                $('#c6r122').data({linkId:"c6r122", title:"ERA", hiperLink:"http://www.era.com/"});
                $('#c6r123').data({linkId:"c6r123", title:"Foreclosures", hiperLink:"http://www.foreclosures.com/"});
                $('#c6r124').data({linkId:"c6r124", title:"Great Schools", hiperLink:"http://www.greatschools.net/"});
                $('#c6r125').data({linkId:"c6r125", title:"Home Gain", hiperLink:"http://www.homegain.com/"});
                $('#c6r126').data({linkId:"c6r126", title:"MLSListings", hiperLink:"http://www.mlslistings.com/"});
                $('#c6r127').data({linkId:"c6r127", title:"Move", hiperLink:"http://www.move.com/"});
                $('#c6r128').data({linkId:"c6r128", title:"Movoto", hiperLink:"http://www.movoto.com/"});
                $('#c6r129').data({linkId:"c6r129", title:"Murray Realty", hiperLink:"http://www.murraykyrealestate.com/"});
                $('#c6r130').data({linkId:"c6r130", title:"NCES", hiperLink:"http://nces.ed.gov/"});
                $('#c6r131').data({linkId:"c6r131", title:"Newhome Source", hiperLink:"http://www.newhomesource.com/"});
                $('#c6r132').data({linkId:"c6r132", title:"Nextag", hiperLink:"http://www.nextag.com/"});
                $('#c6r133').data({linkId:"c6r133", title:"Pacific Union", hiperLink:"http://www.pacunion.com/"});
                $('#c6r134').data({linkId:"c6r134", title:"Realtor", hiperLink:"http://www.realtor.com/"});
                $('#c6r135').data({linkId:"c6r135", title:"Redfin", hiperLink:"http://www.redfin.com/"});
                $('#c6r136').data({linkId:"c6r136", title:"REIL", hiperLink:"http://www.reil.com/"});
                $('#c6r137').data({linkId:"c6r137", title:"Remax", hiperLink:"http://www.remax.com/"});
                $('#c6r138').data({linkId:"c6r138", title:"Rent", hiperLink:"http://www.rent.com/"});
                $('#c6r139').data({linkId:"c6r139", title:"Sanramon Online", hiperLink:"http://www.sanramononline.com/"});
                $('#c6r140').data({linkId:"c6r140", title:"Trulia", hiperLink:"http://www.trulia.com/"});
                $('#c6r141').data({linkId:"c6r141", title:"Yahoo!Realestate", hiperLink:"http://realestate.yahoo.com/"});
                $('#c6r142').data({linkId:"c6r142", title:"Zillow", hiperLink:"http://www.zillow.com/"});
                $('#c6r143').data({linkId:"c6r143", title:"ZIP Reality", hiperLink:"http://www.ziprealty.com/"});
                $('#c7r144').data({linkId:"c7r144", title:"Andhra Bhoomi", hiperLink:"http://www.andhrabhoomi.net/"});
                $('#c7r145').data({linkId:"c7r145", title:"Andhra Guyz", hiperLink:"http://www.andhraguyz.com/"});
                $('#c7r146').data({linkId:"c7r146", title:"Andhra Jyothi", hiperLink:"http://www.andhrajyothy.com/"});
                $('#c7r147').data({linkId:"c7r147", title:"Andhra Prabha", hiperLink:"http://www.andhraprabha.com/"});
                $('#c7r148').data({linkId:"c7r148", title:"BBC", hiperLink:"http://news.bbc.co.uk/"});
                $('#c7r149').data({linkId:"c7r149", title:"CNN", hiperLink:"http://www.cnn.com/"});
                $('#c7r150').data({linkId:"c7r150", title:"Deccan", hiperLink:"http://www.deccan.com/"});
                $('#c7r151').data({linkId:"c7r151", title:"Eenadu", hiperLink:"http://www.eenadu.net/"});
                $('#c7r152').data({linkId:"c7r152", title:"Eenadu Info", hiperLink:"http://www.eenaduinfo.com/links.htm"});
                $('#c7r153').data({linkId:"c7r153", title:"Fox News", hiperLink:"http://www.foxnews.com/"});
                $('#c7r154').data({linkId:"c7r154", title:"Google News", hiperLink:"http://news.google.com/"});
                $('#c7r155').data({linkId:"c7r155", title:"The Hindu", hiperLink:"http://www.thehindu.com/"});
                $('#c7r156').data({linkId:"c7r156", title:"Hindustan Times", hiperLink:"http://www.hindustantimes.com/"});
                $('#c7r157').data({linkId:"c7r157", title:"Mercury News", hiperLink:"http://www.mercurynews.com/"});
                $('#c7r158').data({linkId:"c7r158", title:"NBC", hiperLink:"http://www.nbc.com/"});
                $('#c7r159').data({linkId:"c7r159", title:"NDTV", hiperLink:"http://www.ndtv.com/"});
                $('#c7r160').data({linkId:"c7r160", title:"Namaste Telangana", hiperLink:"http://www.namasthetelangaana.com/"});
                $('#c7r161').data({linkId:"c7r161", title:"Prajashakti", hiperLink:"http://www.prajasakti.com/"});
                $('#c7r162').data({linkId:"c7r162", title:"Pramukhandhra", hiperLink:"http://www.pramukhandhra.org/"});
                $('#c7r163').data({linkId:"c7r163", title:"Rediff", hiperLink:"http://www.rediff.com/"});
                $('#c7r164').data({linkId:"c7r164", title:"Sakshi", hiperLink:"http://www.sakshi.com/"});
                $('#c7r165').data({linkId:"c7r165", title:"Samachar", hiperLink:"http://www.samachar.com/"});
                $('#c7r166').data({linkId:"c7r166", title:"Times of India", hiperLink:"http://timesofindia.indiatimes.com/"});
                $('#c7r167').data({linkId:"c7r167", title:"Vaartha", hiperLink:"http://www.vaarttha.com/"});
                $('#c7r168').data({linkId:"c7r168", title:"Vaarthalu", hiperLink:"http://www.vaartalu.com/"});
                $('#c7r169').data({linkId:"c7r169", title:"Washingtonpost", hiperLink:"http://www.washingtonpost.com/"});
                $('#c7r170').data({linkId:"c7r170", title:"Yahoo!News", hiperLink:"http://news.yahoo.com/"});
                $('#c8r171').data({linkId:"c8r171", title:"American Express", hiperLink:"http://www.americanexpress.com/"});
                $('#c8r172').data({linkId:"c8r172", title:"Ameritrade", hiperLink:"http://www.tdameritrade.com/"});
                $('#c8r173').data({linkId:"c8r173", title:"Bank of America", hiperLink:"http://www.bankofamerica.com/"});
                $('#c8r174').data({linkId:"c8r174", title:"Cisco 401(k)", hiperLink:"http://www.mycisco401k.com/"});
                $('#c8r175').data({linkId:"c8r175", title:"Citibank Cards", hiperLink:"http://www.citicards.com/"});
                $('#c8r176').data({linkId:"c8r176", title:"DCU", hiperLink:"http://www.dcu.org/"});
                $('#c8r177').data({linkId:"c8r177", title:"Discover Card", hiperLink:"http://www.discovercard.com/"});
                $('#c8r178').data({linkId:"c8r178", title:"Experian", hiperLink:"https://partner.experiandirect.com/Premium/Login.aspx"});
                $('#c8r179').data({linkId:"c8r179", title:"Fidelity", hiperLink:"http://www.fidelity.com/"});
                $('#c8r180').data({linkId:"c8r180", title:"Fremont Bank", hiperLink:"http://www.fremontbank.com/"});
                $('#c8r181').data({linkId:"c8r181", title:"ICICI Bank", hiperLink:"http://www.icicibank.com/"});
                $('#c8r182').data({linkId:"c8r182", title:"ING Direct", hiperLink:"http://www.ingdirect.com/"});
                $('#c8r183').data({linkId:"c8r183", title:"ING Vysya", hiperLink:"https://online.ingvysyabank.com/"});
                $('#c8r184').data({linkId:"c8r184", title:"JCPenny", hiperLink:"http://www.jcpenny.com/"});
                $('#c8r185').data({linkId:"c8r185", title:"Macys", hiperLink:"http://www.macys.com/"});
                $('#c8r186').data({linkId:"c8r186", title:"Mervyns", hiperLink:"http://www.mervyns.com/"});
                $('#c8r187').data({linkId:"c8r187", title:"Money2India", hiperLink:"http://www.icicibank.com/pfsuser/icicibank/ibank-nri/nrinewversion/money_transfer/index.html"});
                $('#c8r188').data({linkId:"c8r188", title:"PayPal", hiperLink:"http://www.paypal.com/"});
                $('#c8r189').data({linkId:"c8r189", title:"Smith Barney", hiperLink:"https://www.benefitaccess.com/"});
                $('#c8r190').data({linkId:"c8r190", title:"T Rowe Price", hiperLink:"http://www.troweprice.com/"});
                $('#c8r191').data({linkId:"c8r191", title:"Wamu", hiperLink:"http://www.wamu.com/"});
                $('#c9r192').data({linkId:"c9r192", title:"A9", hiperLink:"http://www.a9.com/"});
                $('#c9r193').data({linkId:"c9r193", title:"Amazon", hiperLink:"http://www.amazon.com/"});
                $('#c9r194').data({linkId:"c9r194", title:"Best Buy", hiperLink:"http://www.bestbuy.com/"});
                $('#c9r195').data({linkId:"c9r195", title:"Biz Rate", hiperLink:"http://www.bizrate.com/"});
                $('#c9r196').data({linkId:"c9r196", title:"Buy", hiperLink:"http://www.buy.com/"});
                $('#c9r197').data({linkId:"c9r197", title:"Circuit City", hiperLink:"http://www.circuitcity.com/"});
                $('#c9r198').data({linkId:"c9r198", title:"Costco", hiperLink:"http://www.costco.com/"});
                $('#c9r199').data({linkId:"c9r199", title:"Craigs List", hiperLink:"http://www.craigslist.com/"});
                $('#c9r200').data({linkId:"c9r200", title:"Deals2Buy", hiperLink:"http://www.deals2buy.com/"});
                $('#c9r201').data({linkId:"c9r201", title:"eBay", hiperLink:"http://www.ebay.com/"});
                $('#c9r202').data({linkId:"c9r202", title:"Froogle", hiperLink:"http://www.froogle.com/"});
                $('#c9r203').data({linkId:"c9r203", title:"Frys/Outpost", hiperLink:"http://www.outpost.com/"});
                $('#c9r204').data({linkId:"c9r204", title:"JCPenny", hiperLink:"http://www.jcpenny.com/"});
                $('#c9r205').data({linkId:"c9r205", title:"Macys", hiperLink:"http://www.macys.com/"});
                $('#c9r206').data({linkId:"c9r206", title:"MySimon", hiperLink:"http://www.mysimon.com/"});
                $('#c9r207').data({linkId:"c9r207", title:"Overstock", hiperLink:"http://www.overstock.com/"});
                $('#c9r208').data({linkId:"c9r208", title:"Penny Saver", hiperLink:"http://www.pennysaverusa.com/"});
                $('#c9r209').data({linkId:"c9r209", title:"Pricegrabber", hiperLink:"http://www.pricegrabber.com/"});
                $('#c9r210').data({linkId:"c9r210", title:"Sears", hiperLink:"http://www.sears.com/"});
                $('#c9r211').data({linkId:"c9r211", title:"Shutterfly", hiperLink:"http://www.shutterfly.com/"});
                $('#c9r212').data({linkId:"c9r212", title:"Snapfish", hiperLink:"http://www.snapfish.com/"});
                $('#c9r213').data({linkId:"c9r213", title:"Sony Style", hiperLink:"http://www.sonystyle.com/"});
                $('#c9r214').data({linkId:"c9r214", title:"Target", hiperLink:"http://www.target.com/"});
                $('#c9r215').data({linkId:"c9r215", title:"Walmart", hiperLink:"http://www.walmart.com/"});
                $('#c9r216').data({linkId:"c9r216", title:"Wirefly", hiperLink:"http://www.wirefly.com/"});
                $('#c9r217').data({linkId:"c9r217", title:"Woot!", hiperLink:"http://www.woot.com/"});
                $('#c9r218').data({linkId:"c9r218", title:"Yelp", hiperLink:"http://www.yelp.com/"});
                $('#c10r219').data({linkId:"c10r219", title:"CIO Insight", hiperLink:"http://www.cioinsight.com/"});
                $('#c10r220').data({linkId:"c10r220", title:"CNET News", hiperLink:"http://news.cnet.com/"});
                $('#c10r221').data({linkId:"c10r221", title:"Google Code", hiperLink:"http://code.google.com/"});
                $('#c10r222').data({linkId:"c10r222", title:"Hibernate", hiperLink:"http://www.hibernate.org/"});
                $('#c10r223').data({linkId:"c10r223", title:"Internet", hiperLink:"http://www.internet.com/"});
                $('#c10r224').data({linkId:"c10r224", title:"J2EE Patterns", hiperLink:"http://www.corej2eepatterns.com/"});
                $('#c10r225').data({linkId:"c10r225", title:"Jakarta", hiperLink:"http://jakarta.apache.org/"});
                $('#c10r226').data({linkId:"c10r226", title:"Java.About", hiperLink:"http://java.about.com/"});
                $('#c10r227').data({linkId:"c10r227", title:"Java Boutique", hiperLink:"http://javaboutique.internet.com/"});
                $('#c10r228').data({linkId:"c10r228", title:"Javadev Journal", hiperLink:"http://java.sys-con.com/"});
                $('#c10r229').data({linkId:"c10r229", title:"Java Ranch", hiperLink:"http://www.javaranch.com/"});
                $('#c10r230').data({linkId:"c10r230", title:"JavaScript.com", hiperLink:"http://javascript.com/"});
                $('#c10r231').data({linkId:"c10r231", title:"JS@Internet.com", hiperLink:"http://javascript.internet.com/"});
                $('#c10r232').data({linkId:"c10r232", title:"Java World", hiperLink:"http://www.javaworld.com/"});
                $('#c10r233').data({linkId:"c10r233", title:"Lazy DBA", hiperLink:"http://www.lazydba.com/"});
                $('#c10r234').data({linkId:"c10r234", title:"MySQL", hiperLink:"http://www.mysql.com/"});
                $('#c10r235').data({linkId:"c10r235", title:"OnJava", hiperLink:"http://www.onjava.com/"});
                $('#c10r236').data({linkId:"c10r236", title:"Oracle", hiperLink:"http://www.oracle.com/"});
                $('#c10r237').data({linkId:"c10r237", title:"Orafaq", hiperLink:"http://www.orafaq.com/"});
                $('#c10r238').data({linkId:"c10r238", title:"PSOUG", hiperLink:"http://www.psoug.org/"});
                $('#c10r239').data({linkId:"c10r239", title:"QA Test", hiperLink:"http://www.softwareqatest.com/"});
                $('#c10r240').data({linkId:"c10r240", title:"Technology Review", hiperLink:"http://www.technologyreview.com/"});
                $('#c10r241').data({linkId:"c10r241", title:"The Serverside", hiperLink:"http://www.theserverside.com/"});
                $('#c10r242').data({linkId:"c10r242", title:"Spring", hiperLink:"http://www.springframework.org/"});
                $('#c10r243').data({linkId:"c10r243", title:"Sun Java", hiperLink:"http://java.sun.com/"});
                $('#c10r244').data({linkId:"c10r244", title:"W3 Schools", hiperLink:"http://www.w3schools.com/"});
                $('#c10r245').data({linkId:"c10r245", title:"Yahoo!Developer", hiperLink:"http://developer.yahoo.com/"});
                $('#c10r246').data({linkId:"c10r246", title:"Mind Tools", hiperLink:"http://www.mindtools.com/"});
                $('#c10r247').data({linkId:"c10r247", title:"Quick MBA", hiperLink:"http://www.quickmba.com/"});
                $('#c11r248').data({linkId:"c11r248", title:"AC Library", hiperLink:"http://www.aclibrary.org/"});
                $('#c11r249').data({linkId:"c11r249", title:"AT&T", hiperLink:"http://www.wireless.att.com/"});
                $('#c11r250').data({linkId:"c11r250", title:"Cisco Mail", hiperLink:"http://email.cisco.com/"});
                $('#c11r251').data({linkId:"c11r251", title:"Comcast", hiperLink:"http://www.comcast.com/"});
                $('#c11r252').data({linkId:"c11r252", title:"Facebook", hiperLink:"https://www.facebook.com/"});
                $('#c11r253').data({linkId:"c11r253", title:"Farmers", hiperLink:"http://www.farmers.com/"});
                $('#c11r254').data({linkId:"c11r254", title:"Fastrak", hiperLink:"https://www.bayareafastrak.org/"});
                $('#c11r255').data({linkId:"c11r255", title:"Gmail", hiperLink:"http://www.gmail.com/"});
                $('#c11r256').data({linkId:"c11r256", title:"Hotmail", hiperLink:"http://www.hotmail.com/"});
                $('#c11r257').data({linkId:"c11r257", title:"LinkedIn", hiperLink:"http://www.linkedin.com/"});
                $('#c11r258').data({linkId:"c11r258", title:"Pacificare", hiperLink:"http://www.pacificare.com/"});
                $('#c11r259').data({linkId:"c11r259", title:"PAMFOnline", hiperLink:"http://www.pamfonline.org/"});
                $('#c11r260').data({linkId:"c11r260", title:"PG&E", hiperLink:"http://www.pge.com/"});
                $('#c11r261').data({linkId:"c11r261", title:"Reliance Call", hiperLink:"http://www.relianceindiacall.com/US/Index.asp"});
                $('#c11r262').data({linkId:"c11r262", title:"SutiSoft Mail", hiperLink:"http://mail.sutisoft.com/"});
                $('#c11r263').data({linkId:"c11r263", title:"United Healthcare", hiperLink:"http://www.myuhc.com/"});
                $('#c11r264').data({linkId:"c11r264", title:"Vonage", hiperLink:"http://www.vonage.com/"});
                $('#c11r265').data({linkId:"c11r265", title:"Yahoo!Mail", hiperLink:"http://mail.yahoo.com/"});
            }
           
        </script>

        <style type="text/css">
        </style>
    </head>


    <body>

        <div class="dockingBarTop">
            <div class="top-column-element-left">
                <div class="company-logo">
                    <img id="logo" src="images/bukmarcs.png"/>
                    BukMarxs<sup>&reg;</sup>
                </div>
                <div class="company-logo-punchline">
                    Your Own. Personal World!
                </div>
            </div>
            <div class="top-column-element-middle">
                Buy Samsung TV Today!
            </div>
            <div class="top-column-element-right">

                <img class ="photo-box" src="images/user.jpg" alt="Welcome, Alwal Putta"/>
                <ul id="menu">
                    <li>
                        <a href="login">Login</a>
                        <ul>
                            <li>
                                <a href="">Welcome!</a>
                            </li>
                            <li>
                                <a href="edit_preferences">Edit Preferences</a>
                            </li>
                        </ul>
                    </li>
                    <li><a href="options">Options</a>
                        <ul>
                            <li>
                                <a href="add_category">Add Category</a>
                            </li>
                            <li>
                                <a href="add_bookmark">Add Bookmark</a>
                            </li>
                            <li>
                                <a href="import_bookmarks">Import Bookmarks</a>
                            </li>
                            <li>
                                <a href="industry_trends">Industry Trends</a>
                            </li>
                            <li>
                                <a href="personal_reports">Personal Reports</a>
                            </li>
                        </ul>
                    </li>
                    <li><a href="help">Help</a> 
                        <ul>
                            <li>
                                <a href="release_notes">Release Notes</a>
                            </li>
                            <li>
                                <a href="quick_guide">Quick Guide</a>
                            </li>
                            <li>
                                <a href="user_guide">User Guide</a>
                            </li>
                        </ul>
                    </li>
                    <li><a href="logout">Logout</a>
                        <ul>
                            <li>
                                <a href="logout">Logout.</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <br></br>
                <form id="searchForm">
                    <fieldset title="Search Form" id="search-form-fieldset">
                        <div class="search-box">
                            <input type="text" id="search-input" name="search-input"/>
                            <input type="button" id="search-button" name="search-button"/>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>


        <div class="mask"></div>

        <div class="message"></div>

        <div class="loading">Page being loaded, please wait ...</div>

        <div class="middle-column-element-edit-icons">
            <img class="ui-icon ui-icon-pencil middle-column-element-edit-icon"></img>
            <img class="ui-icon ui-icon-trash middle-column-element-delete-icon"></img>
        </div>

        <div class="middle-row-element-edit-icons">
            <img class="ui-icon ui-icon-pencil middle-row-element-edit-icon"></img>
            <img class="ui-icon ui-icon-trash middle-row-element-delete-icon"></img>
        </div>

        <div class="inline-column-element-edit">
            <input type="text" maxlength="100" class="inline-column-edit-input"/>
            <input type="button" value="Save"/>
            <input type="reset" value="Reset"/>
            <input type="button" value="Cancel"/>
        </div>

        <div class="inline-row-element-edit">
            edit row.
            <input type="input" class="inline-row-edit-input" id="inline-row-title" value=""></input>
            <input type="input" class="inline-row-edit-input" id="inline-row-hiperLink" value=""></input>
            <textarea cols="3" class="inline-row-edit-text" id="inline-row-description"></textarea>
            <input type="button" id="inline-save" value="Save"></input>
            <input type="reset" id="inline-save" value="Reset"></input>
            <input type="button" id="inline-save" value="Close"></input>
        </div>



        <div class="edit-element-modal">
            <div class="edit-element">
                <div>EDIT ELEMENT:</div>
                <form title="Midify Element" id="edit-element_form">
                    <fieldset>
                        <legend>Edit Element</legend>
                        <input type="text" name="element-name" id="element-name" size="60" maxlength="200"/>
                        <br></br>
                        <input type="text" name="url" id="url" size="60" maxlength="200"/>
                        <br></br>
                        <textarea cols="60" rows="4" id="description" name="description"></textarea>
                        <br></br>
                        <input type="submit" name="save" id="save-element" value="Save" class="edit-element-button"/>
                        <input type="reset" name="cancel" value="Cancel" class="edit-element-button"/>
                    </fieldset>
                </form>
            </div>
        </div>




        <div class="new-element-modal">
            <div class="new-element">
                <form title="Add Element" id="new-element_form" action="">
                    <fieldset>
                        <legend>New Element:</legend>
                        <label>
                            <span>Group Name:</span>
                            <input type="text" name="group-name" id="group-name" size="50" maxlength="200"/></label>
                        <br></br>
                        <label>
                            <span>Element Name/Title:</span>
                            <input type="text" name="element-name" id="element-name" size="60" maxlength="200"/></label>
                        <br></br>
                        <label>
                            <span>Element Address/URL:</span>
                            <input type="text" name="url" id="url" size="60" maxlength="200"/>
                        </label>
                        <br></br>
                        <label>
                            <span>Description:</span>
                            <textarea cols="60" rows="4" id="description" name="description"></textarea>
                        </label>
                        <br></br>
                        <input type="submit" name="save" id="save-element" value="Save" class="new-element-button"/>
                        <input type="reset" name="cancel" value="Cancel" class="new-element-button"/>
                        <input type="button" name="close" value="Close" class="login-button"/>
                        <br></br>
                        <a href="#register_faq" class="new-element-links">Add/Edit Element FAQ</a>
                    </fieldset>
                </form>
            </div>
        </div>





        <div class="dockingBarBottom">
            <!--ul class="bottom-row-item"-->
            <div class="bottom-row-item" onclick="load_bottom_bar('1')">About Links Oasis &reg;</div>
            <div class="bottom-row-item" onclick="load_bottom_bar('2')">EULA</div>
            <div class="bottom-row-item" onclick="load_bottom_bar('3')">User Guide/Help</div>
            <div class="bottom-row-item" onclick="load_bottom_bar('4')"><a href="#">Feedback</a></div>
            <div class="bottom-row-item" onclick="load_bottom_bar('5')"><a href="#">Refer Friends</a></div>
        </div>



        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>




