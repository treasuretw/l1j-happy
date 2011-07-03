package l1j.ABC;


//import java.util.ArrayList;
//import java.util.StringTokenizer;
import java.sql.*;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;

public class Place {
    public static String[] Crown() throws SQLException { //王族等級排序
        String no1 = "空缺";
        String no2 = "空缺";
        String no3 = "空缺";
        String no4 = "空缺";
        String no5 = "空缺";
        String no6 = "?";
        String no7 = "?";
        String no8 = "?";
        String no9 = "?";
        String no10 = "?";
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        PreparedStatement pstm3 = null;
        PreparedStatement pstm4 = null;
        PreparedStatement pstm5 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        Connection con = null;
        String[] levelresult = null;
        con = L1DatabaseFactory.getInstance().getConnection();
// 主要內容有：GM不列入排序、篩選出王族男女CLASSID、經驗值由大到小排列。因為等級是依照經驗值來計算的，所以不搜尋等級，而搜尋經驗值，同等級經驗值高者名次較前。最後是從第一筆往下取一筆資料、從第二筆往下取一筆資料這樣。)
        pstm1 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 0 || class = 1) order by exp DESC limit 0,1;");
        pstm2 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 0 || class = 1) order by exp DESC limit 1,1;");
        pstm3 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 0 || class = 1) order by exp DESC limit 2,1;");
        pstm4 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 0 || class = 1) order by exp DESC limit 3,1;");
        pstm5 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 0 || class = 1) order by exp DESC limit 4,1;");

        rs1 = pstm1.executeQuery();
        if (rs1.next()) {
            no1 = rs1.getString("char_name");
            no6 = rs1.getString("level");
        }
        rs2 = pstm2.executeQuery();
        if (rs2.next()) {
            no2 = rs2.getString("char_name");
            no7 = rs2.getString("level");
        }
        rs3 = pstm3.executeQuery();
        if (rs3.next()) {
            no3 = rs3.getString("char_name");
            no8 = rs3.getString("level");
        }
        rs4 = pstm4.executeQuery();
        if (rs4.next()) {
            no4 = rs4.getString("char_name");
            no9 = rs4.getString("level");
        }
        rs5 = pstm5.executeQuery();
        if (rs5.next()) {
            no5 = rs5.getString("char_name");
            no10 = rs5.getString("level");
        }
        levelresult = new String[] {"", String.valueOf(no1), String.valueOf(no2), String.valueOf(no3), String.valueOf(no4), String.valueOf(no5)
                                        , String.valueOf(no6), String.valueOf(no7), String.valueOf(no8), String.valueOf(no9), String.valueOf(no10)};
        SQLUtil.close(rs1);
        SQLUtil.close(rs2);
        SQLUtil.close(rs3);
        SQLUtil.close(rs4);
        SQLUtil.close(rs5);
        SQLUtil.close(pstm1);
        SQLUtil.close(pstm2);
        SQLUtil.close(pstm3);
        SQLUtil.close(pstm4);
        SQLUtil.close(pstm5);
        SQLUtil.close(con);
        return levelresult;
    }
    public static String[] Elf() throws SQLException { //妖精等級排序
        String no1 = "空缺";
        String no2 = "空缺";
        String no3 = "空缺";
        String no4 = "空缺";
        String no5 = "空缺";
        String no6 = "?";
        String no7 = "?";
        String no8 = "?";
        String no9 = "?";
        String no10 = "?";
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        PreparedStatement pstm3 = null;
        PreparedStatement pstm4 = null;
        PreparedStatement pstm5 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        Connection con = null;
        String[] levelresult = null;
        con = L1DatabaseFactory.getInstance().getConnection();
// 主要內容有：GM不列入排序、篩選出妖精男女CLASSID、經驗值由大到小排列。因為等級是依照經驗值來計算的，所以不搜尋等級，而搜尋經驗值，同等級經驗值高者名次較前。最後是從第一筆往下取一筆資料、從第二筆往下取一筆資料這樣。)
        pstm1 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 138 || class = 37) order by exp DESC limit 0,1;");
        pstm2 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 138 || class = 37) order by exp DESC limit 1,1;");
        pstm3 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 138 || class = 37) order by exp DESC limit 2,1;");
        pstm4 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 138 || class = 37) order by exp DESC limit 3,1;");
        pstm5 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 138 || class = 37) order by exp DESC limit 4,1;");

        rs1 = pstm1.executeQuery();
        if (rs1.next()) {
            no1 = rs1.getString("char_name");
            no6 = rs1.getString("level");
        }
        rs2 = pstm2.executeQuery();
        if (rs2.next()) {
            no2 = rs2.getString("char_name");
            no7 = rs2.getString("level");
        }
        rs3 = pstm3.executeQuery();
        if (rs3.next()) {
            no3 = rs3.getString("char_name");
            no8 = rs3.getString("level");
        }
        rs4 = pstm4.executeQuery();
        if (rs4.next()) {
            no4 = rs4.getString("char_name");
            no9 = rs4.getString("level");
        }
        rs5 = pstm5.executeQuery();
        if (rs5.next()) {
            no5 = rs5.getString("char_name");
            no10 = rs5.getString("level");
        }
        levelresult = new String[] {"", String.valueOf(no1), String.valueOf(no2), String.valueOf(no3), String.valueOf(no4), String.valueOf(no5)
                                        , String.valueOf(no6), String.valueOf(no7), String.valueOf(no8), String.valueOf(no9), String.valueOf(no10)};
        SQLUtil.close(rs1);
        SQLUtil.close(rs2);
        SQLUtil.close(rs3);
        SQLUtil.close(rs4);
        SQLUtil.close(rs5);
        SQLUtil.close(pstm1);
        SQLUtil.close(pstm2);
        SQLUtil.close(pstm3);
        SQLUtil.close(pstm4);
        SQLUtil.close(pstm5);
        SQLUtil.close(con);
        return levelresult;
    }
    public static String[] Wizard() throws SQLException { //法師等級排序
        String no1 = "空缺";
        String no2 = "空缺";
        String no3 = "空缺";
        String no4 = "空缺";
        String no5 = "空缺";
        String no6 = "?";
        String no7 = "?";
        String no8 = "?";
        String no9 = "?";
        String no10 = "?";
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        PreparedStatement pstm3 = null;
        PreparedStatement pstm4 = null;
        PreparedStatement pstm5 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        Connection con = null;
        String[] levelresult = null;
        con = L1DatabaseFactory.getInstance().getConnection();
// 主要內容有：GM不列入排序、篩選出法師男女CLASSID、經驗值由大到小排列。因為等級是依照經驗值來計算的，所以不搜尋等級，而搜尋經驗值，同等級經驗值高者名次較前。最後是從第一筆往下取一筆資料、從第二筆往下取一筆資料這樣。)
        pstm1 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 1186 || class = 734) order by exp DESC limit 0,1;");
        pstm2 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 1186 || class = 734) order by exp DESC limit 1,1;");
        pstm3 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 1186 || class = 734) order by exp DESC limit 2,1;");
        pstm4 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 1186 || class = 734) order by exp DESC limit 3,1;");
        pstm5 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 1186 || class = 734) order by exp DESC limit 4,1;");

        rs1 = pstm1.executeQuery();
        if (rs1.next()) {
            no1 = rs1.getString("char_name");
            no6 = rs1.getString("level");
        }
        rs2 = pstm2.executeQuery();
        if (rs2.next()) {
            no2 = rs2.getString("char_name");
            no7 = rs2.getString("level");
        }
        rs3 = pstm3.executeQuery();
        if (rs3.next()) {
            no3 = rs3.getString("char_name");
            no8 = rs3.getString("level");
        }
        rs4 = pstm4.executeQuery();
        if (rs4.next()) {
            no4 = rs4.getString("char_name");
            no9 = rs4.getString("level");
        }
        rs5 = pstm5.executeQuery();
        if (rs5.next()) {
            no5 = rs5.getString("char_name");
            no10 = rs5.getString("level");
        }
        levelresult = new String[] {"", String.valueOf(no1), String.valueOf(no2), String.valueOf(no3), String.valueOf(no4), String.valueOf(no5)
                                        , String.valueOf(no6), String.valueOf(no7), String.valueOf(no8), String.valueOf(no9), String.valueOf(no10)};
        SQLUtil.close(rs1);
        SQLUtil.close(rs2);
        SQLUtil.close(rs3);
        SQLUtil.close(rs4);
        SQLUtil.close(rs5);
        SQLUtil.close(pstm1);
        SQLUtil.close(pstm2);
        SQLUtil.close(pstm3);
        SQLUtil.close(pstm4);
        SQLUtil.close(pstm5);
        SQLUtil.close(con);
        return levelresult;
    }   
    public static String[] Knight() throws SQLException { //騎士等級排序
        String no1 = "空缺";
        String no2 = "空缺";
        String no3 = "空缺";
        String no4 = "空缺";
        String no5 = "空缺";
        String no6 = "?";
        String no7 = "?";
        String no8 = "?";
        String no9 = "?";
        String no10 = "?";
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        PreparedStatement pstm3 = null;
        PreparedStatement pstm4 = null;
        PreparedStatement pstm5 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        Connection con = null;
        String[] levelresult = null;
        con = L1DatabaseFactory.getInstance().getConnection();
// 主要內容有：GM不列入排序、篩選出騎士男女CLASSID、經驗值由大到小排列。因為等級是依照經驗值來計算的，所以不搜尋等級，而搜尋經驗值，同等級經驗值高者名次較前。最後是從第一筆往下取一筆資料、從第二筆往下取一筆資料這樣。)
        pstm1 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 61 || class = 48) order by exp DESC limit 0,1;");
        pstm2 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 61 || class = 48) order by exp DESC limit 1,1;");
        pstm3 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 61 || class = 48) order by exp DESC limit 2,1;");
        pstm4 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 61 || class = 48) order by exp DESC limit 3,1;");
        pstm5 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 61 || class = 48) order by exp DESC limit 4,1;");

        rs1 = pstm1.executeQuery();
        if (rs1.next()) {
            no1 = rs1.getString("char_name");
            no6 = rs1.getString("level");
        }
        rs2 = pstm2.executeQuery();
        if (rs2.next()) {
            no2 = rs2.getString("char_name");
            no7 = rs2.getString("level");
        }
        rs3 = pstm3.executeQuery();
        if (rs3.next()) {
            no3 = rs3.getString("char_name");
            no8 = rs3.getString("level");
        }
        rs4 = pstm4.executeQuery();
        if (rs4.next()) {
            no4 = rs4.getString("char_name");
            no9 = rs4.getString("level");
        }
        rs5 = pstm5.executeQuery();
        if (rs5.next()) {
            no5 = rs5.getString("char_name");
            no10 = rs5.getString("level");
        }
        levelresult = new String[] {"", String.valueOf(no1), String.valueOf(no2), String.valueOf(no3), String.valueOf(no4), String.valueOf(no5)
                                        , String.valueOf(no6), String.valueOf(no7), String.valueOf(no8), String.valueOf(no9), String.valueOf(no10)};
        SQLUtil.close(rs1);
        SQLUtil.close(rs2);
        SQLUtil.close(rs3);
        SQLUtil.close(rs4);
        SQLUtil.close(rs5);
        SQLUtil.close(pstm1);
        SQLUtil.close(pstm2);
        SQLUtil.close(pstm3);
        SQLUtil.close(pstm4);
        SQLUtil.close(pstm5);
        SQLUtil.close(con);
        return levelresult;
    }   
    public static String[] Darkelf() throws SQLException { //黑妖等級排序
        String no1 = "空缺";
        String no2 = "空缺";
        String no3 = "空缺";
        String no4 = "空缺";
        String no5 = "空缺";
        String no6 = "?";
        String no7 = "?";
        String no8 = "?";
        String no9 = "?";
        String no10 = "?";
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        PreparedStatement pstm3 = null;
        PreparedStatement pstm4 = null;
        PreparedStatement pstm5 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        Connection con = null;
        String[] levelresult = null;
        con = L1DatabaseFactory.getInstance().getConnection();
// 主要內容有：GM不列入排序、篩選出黑妖男女CLASSID、經驗值由大到小排列。因為等級是依照經驗值來計算的，所以不搜尋等級，而搜尋經驗值，同等級經驗值高者名次較前。最後是從第一筆往下取一筆資料、從第二筆往下取一筆資料這樣。)
        pstm1 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 2786 || class = 2796) order by exp DESC limit 0,1;");
        pstm2 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 2786 || class = 2796) order by exp DESC limit 1,1;");
        pstm3 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 2786 || class = 2796) order by exp DESC limit 2,1;");
        pstm4 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 2786 || class = 2796) order by exp DESC limit 3,1;");
        pstm5 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 2786 || class = 2796) order by exp DESC limit 4,1;");

        rs1 = pstm1.executeQuery();
        if (rs1.next()) {
            no1 = rs1.getString("char_name");
            no6 = rs1.getString("level");
        }
        rs2 = pstm2.executeQuery();
        if (rs2.next()) {
            no2 = rs2.getString("char_name");
            no7 = rs2.getString("level");
        }
        rs3 = pstm3.executeQuery();
        if (rs3.next()) {
            no3 = rs3.getString("char_name");
            no8 = rs3.getString("level");
        }
        rs4 = pstm4.executeQuery();
        if (rs4.next()) {
            no4 = rs4.getString("char_name");
            no9 = rs4.getString("level");
        }
        rs5 = pstm5.executeQuery();
        if (rs5.next()) {
            no5 = rs5.getString("char_name");
            no10 = rs5.getString("level");
        }
        levelresult = new String[] {"", String.valueOf(no1), String.valueOf(no2), String.valueOf(no3), String.valueOf(no4), String.valueOf(no5)
                                        , String.valueOf(no6), String.valueOf(no7), String.valueOf(no8), String.valueOf(no9), String.valueOf(no10)};
        SQLUtil.close(rs1);
        SQLUtil.close(rs2);
        SQLUtil.close(rs3);
        SQLUtil.close(rs4);
        SQLUtil.close(rs5);
        SQLUtil.close(pstm1);
        SQLUtil.close(pstm2);
        SQLUtil.close(pstm3);
        SQLUtil.close(pstm4);
        SQLUtil.close(pstm5);
        SQLUtil.close(con);
        return levelresult;
    }
    public static String[] Dragon() throws SQLException { //龍騎士等級排序
        String no1 = "空缺";
        String no2 = "空缺";
        String no3 = "空缺";
        String no4 = "空缺";
        String no5 = "空缺";
        String no6 = "?";
        String no7 = "?";
        String no8 = "?";
        String no9 = "?";
        String no10 = "?";
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        PreparedStatement pstm3 = null;
        PreparedStatement pstm4 = null;
        PreparedStatement pstm5 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        Connection con = null;
        String[] levelresult = null;
        con = L1DatabaseFactory.getInstance().getConnection();
// 主要內容有：GM不列入排序、篩選出龍騎士男女CLASSID、經驗值由大到小排列。因為等級是依照經驗值來計算的，所以不搜尋等級，而搜尋經驗值，同等級經驗值高者名次較前。最後是從第一筆往下取一筆資料、從第二筆往下取一筆資料這樣。)
        pstm1 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6658 || class = 6661) order by exp DESC limit 0,1;");
        pstm2 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6658 || class = 6661) order by exp DESC limit 1,1;");
        pstm3 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6658 || class = 6661) order by exp DESC limit 2,1;");
        pstm4 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6658 || class = 6661) order by exp DESC limit 3,1;");
        pstm5 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6658 || class = 6661) order by exp DESC limit 4,1;");

        rs1 = pstm1.executeQuery();
        if (rs1.next()) {
            no1 = rs1.getString("char_name");
            no6 = rs1.getString("level");
        }
        rs2 = pstm2.executeQuery();
        if (rs2.next()) {
            no2 = rs2.getString("char_name");
            no7 = rs2.getString("level");
        }
        rs3 = pstm3.executeQuery();
        if (rs3.next()) {
            no3 = rs3.getString("char_name");
            no8 = rs3.getString("level");
        }
        rs4 = pstm4.executeQuery();
        if (rs4.next()) {
            no4 = rs4.getString("char_name");
            no9 = rs4.getString("level");
        }
        rs5 = pstm5.executeQuery();
        if (rs5.next()) {
            no5 = rs5.getString("char_name");
            no10 = rs5.getString("level");
        }
        levelresult = new String[] {"", String.valueOf(no1), String.valueOf(no2), String.valueOf(no3), String.valueOf(no4), String.valueOf(no5)
                                        , String.valueOf(no6), String.valueOf(no7), String.valueOf(no8), String.valueOf(no9), String.valueOf(no10)};
        SQLUtil.close(rs1);
        SQLUtil.close(rs2);
        SQLUtil.close(rs3);
        SQLUtil.close(rs4);
        SQLUtil.close(rs5);
        SQLUtil.close(pstm1);
        SQLUtil.close(pstm2);
        SQLUtil.close(pstm3);
        SQLUtil.close(pstm4);
        SQLUtil.close(pstm5);
        SQLUtil.close(con);
        return levelresult;
    }
    public static String[] Illusionist() throws SQLException { //幻術師等級排序
        String no1 = "空缺";
        String no2 = "空缺";
        String no3 = "空缺";
        String no4 = "空缺";
        String no5 = "空缺";
        String no6 = "?";
        String no7 = "?";
        String no8 = "?";
        String no9 = "?";
        String no10 = "?";
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;
        PreparedStatement pstm3 = null;
        PreparedStatement pstm4 = null;
        PreparedStatement pstm5 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        Connection con = null;
        String[] levelresult = null;
        con = L1DatabaseFactory.getInstance().getConnection();
// 主要內容有：GM不列入排序、篩選出幻術師男女CLASSID、經驗值由大到小排列。因為等級是依照經驗值來計算的，所以不搜尋等級，而搜尋經驗值，同等級經驗值高者名次較前。最後是從第一筆往下取一筆資料、從第二筆往下取一筆資料這樣。)
        pstm1 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6671 || class = 6650) order by exp DESC limit 0,1;");
        pstm2 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6671 || class = 6650) order by exp DESC limit 1,1;");
        pstm3 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6671 || class = 6650) order by exp DESC limit 2,1;");
        pstm4 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6671 || class = 6650) order by exp DESC limit 3,1;");
        pstm5 = con.prepareStatement("select char_name,level from characters where accesslevel = 0 && (class = 6671 || class = 6650) order by exp DESC limit 4,1;");

        rs1 = pstm1.executeQuery();
        if (rs1.next()) {
            no1 = rs1.getString("char_name");
            no6 = rs1.getString("level");
        }
        rs2 = pstm2.executeQuery();
        if (rs2.next()) {
            no2 = rs2.getString("char_name");
            no7 = rs2.getString("level");
        }
        rs3 = pstm3.executeQuery();
        if (rs3.next()) {
            no3 = rs3.getString("char_name");
            no8 = rs3.getString("level");
        }
        rs4 = pstm4.executeQuery();
        if (rs4.next()) {
            no4 = rs4.getString("char_name");
            no9 = rs4.getString("level");
        }
        rs5 = pstm5.executeQuery();
        if (rs5.next()) {
            no5 = rs5.getString("char_name");
            no10 = rs5.getString("level");
        }
        levelresult = new String[] {"", String.valueOf(no1), String.valueOf(no2), String.valueOf(no3), String.valueOf(no4), String.valueOf(no5)
                                        , String.valueOf(no6), String.valueOf(no7), String.valueOf(no8), String.valueOf(no9), String.valueOf(no10)};
        SQLUtil.close(rs1);
        SQLUtil.close(rs2);
        SQLUtil.close(rs3);
        SQLUtil.close(rs4);
        SQLUtil.close(rs5);
        SQLUtil.close(pstm1);
        SQLUtil.close(pstm2);
        SQLUtil.close(pstm3);
        SQLUtil.close(pstm4);
        SQLUtil.close(pstm5);
        SQLUtil.close(con);
        return levelresult;
    } 
    
}