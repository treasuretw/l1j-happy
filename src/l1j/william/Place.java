package l1j.william;

import java.sql.*;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;

/**
 *	等级排行查询 (前五名)
 */

public class Place {

	//王族等级排序
	public static String[] Crown() throws SQLException {
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

        // 主要内容有：GM不列入排序、筛选出王族男女CLASSID、经验值由大到小排列。因为等级是依照经验值来计算的，所以不搜寻等级，而搜寻经验值，同等级经验值高者名次较前。最后是从第一笔往下取一笔资料、从第二笔往下取一笔资料这样。)
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

	//妖精等级排序
    public static String[] Elf() throws SQLException {
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

        // 主要内容有：GM不列入排序、筛选出妖精男女CLASSID、经验值由大到小排列。因为等级是依照经验值来计算的，所以不搜寻等级，而搜寻经验值，同等级经验值高者名次较前。最后是从第一笔往下取一笔资料、从第二笔往下取一笔资料这样。)
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

  //法师等级排序
    public static String[] Wizard() throws SQLException {
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

        // 主要内容有：GM不列入排序、筛选出法师男女CLASSID、经验值由大到小排列。因为等级是依照经验值来计算的，所以不搜寻等级，而搜寻经验值，同等级经验值高者名次较前。最后是从第一笔往下取一笔资料、从第二笔往下取一笔资料这样。)
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

  //骑士等级排序
    public static String[] Knight() throws SQLException {
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

        // 主要内容有：GM不列入排序、筛选出骑士男女CLASSID、经验值由大到小排列。因为等级是依照经验值来计算的，所以不搜寻等级，而搜寻经验值，同等级经验值高者名次较前。最后是从第一笔往下取一笔资料、从第二笔往下取一笔资料这样。)
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

  //黑妖等级排序
    public static String[] Darkelf() throws SQLException {
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

        // 主要内容有：GM不列入排序、筛选出黑妖男女CLASSID、经验值由大到小排列。因为等级是依照经验值来计算的，所以不搜寻等级，而搜寻经验值，同等级经验值高者名次较前。最后是从第一笔往下取一笔资料、从第二笔往下取一笔资料这样。)
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

  //龙骑士等级排序
    public static String[] Dragon() throws SQLException {
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

        // 主要内容有：GM不列入排序、筛选出龙骑士男女CLASSID、经验值由大到小排列。因为等级是依照经验值来计算的，所以不搜寻等级，而搜寻经验值，同等级经验值高者名次较前。最后是从第一笔往下取一笔资料、从第二笔往下取一笔资料这样。)
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

  //幻术师等级排序
    public static String[] Illusionist() throws SQLException {
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

        // 主要内容有：GM不列入排序、筛选出幻术师男女CLASSID、经验值由大到小排列。因为等级是依照经验值来计算的，所以不搜寻等级，而搜寻经验值，同等级经验值高者名次较前。最后是从第一笔往下取一笔资料、从第二笔往下取一笔资料这样。)
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
