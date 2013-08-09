package com.jingpaidang.toolSystem.dao;

import com.jingpaidang.toolSystem.domain.Keyword;
import com.jingpaidang.toolSystem.util.JdbcUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 7/16/13
 * Time: 6:45 PM
 */
public class KeywordDao {


    private static final Logger logger = LoggerFactory.getLogger(KeywordDao.class);

    /**
     * 单个添加关键词 ，可用的
     * @param keyword
     */
    public void addKeyword(Keyword keyword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into keywords (keyname,createTime,number) values(?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, keyword.getKeyName());
            logger.info("时间是=====" + new Date());
            stmt.setTimestamp(2,new Timestamp((new Date()).getTime()));
            stmt.setInt(3,0);
            stmt.executeUpdate();
            logger.info(keyword.getKeyName() + "===关键词========插入完成");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info(keyword.getKeyName() + "===关键词===========插入出错");
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }

    }

    /**
     *
     * @param keyword
     */
    public void addUnUse(Keyword keyword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into keywords (keyname,createTime) values(?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, keyword.getKeyName());
            logger.info("时间是=====" + new Date());
            stmt.setTimestamp(2,new Timestamp((new Date()).getTime()));
            stmt.executeUpdate();
            logger.info(keyword.getKeyName() + "===关键词========插入完成");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info(keyword.getKeyName() + "===关键词===========插入出错");
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }

    }

    public void saveOrUpdate(Keyword keyword) {
         if(keyword.getId() != 0) {
             updateKeyword(keyword);

         } else {
             addUnUse(keyword);
         }
    }

    public void updateKeyword(Keyword keyword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "update keywords set updateTime=?, number=? where id=?";
            stmt = conn.prepareStatement(sql);

            stmt.setTimestamp(1, new Timestamp(keyword.getUpdateTime().getTime()));
            stmt.setInt(2, keyword.getNumber());
            stmt.setInt(3,keyword.getId());

            stmt.executeUpdate();
            logger.info(keyword.getKeyName() + "===关键词========更新完成");
        } catch (SQLException e) {

            logger.info(keyword.getKeyName() + "===关键词===========更新出错");
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }


    }

    /**
     * 批量添加关键词
     * @param keywordList
     */
    public void addKeywords(List<Keyword> keywordList) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into keywords (keyname,createTime) values(?,?)";
            stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(Keyword keyword : keywordList) {
                stmt.setString(1, keyword.getKeyName());
                stmt.setTimestamp(2,new Timestamp((new Date()).getTime()));
                stmt.addBatch();
            }
            stmt.executeBatch();
            logger.info("关键词================批量插入完成");
        } catch (SQLException e) {
            logger.info("关键词======================批量插入出错");
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }

    } /**
     * 批量添加关键词并返回关键词
     * @param stringList
     */
    public List<Keyword> saveAndfindKeywords(List<String> stringList) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Keyword> allKeyword = this.findAllKeyword();



        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into keywords (keyName,createTime) values(?,?)";
            stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(String keyword : stringList) {
                stmt.setString(1, keyword);
                stmt.setTimestamp(2, new Timestamp((new Date()).getTime()));
                stmt.addBatch();
            }
            stmt.executeBatch();
            return this.findAllKeyword();
        } catch (SQLException e) {
            logger.info("关键词======================批量插入出错");
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }

    }

    /**
     * 批量添加关键词
     * @param keywordList
     */
    public void addKeywordList(List<String> keywordList) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into keywords (keyName,createTime) values(?,?)";
            stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(String keyword : keywordList) {
                stmt.setString(1, keyword);
                stmt.setTimestamp(2,new Timestamp(new Date().getTime()));
                stmt.addBatch();
            }
            stmt.executeBatch();
            logger.info("关键词 ==================批量插入完成");
        } catch (SQLException e) {
            logger.info("关键词======================批量插入出错");
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }

    }

    public Keyword findKeywordByName(String keyName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "select id,keyName from keywords where keyName = '"  + keyName + "'";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                Keyword keyword = new Keyword();
                keyword.setId(rs.getInt(1));
                keyword.setKeyName(rs.getString(2));
                return keyword;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }
    }

    public List<Keyword> findKeywords(List<String> strings) {
        List<Keyword> keywordList = new ArrayList<Keyword>();
        int i =0;
        for(String string : strings) {
            Keyword keywordByName = this.findKeywordByName(string);
            keywordList.add(keywordByName);
            i ++ ;
        }
        return keywordList;
    }


    public void deleteKeywordByTime() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "delete from skus where keyId in (select k.id from keywords k where DATEDIFF(curdate(),date(createTime)) > 15 and number = 0 )" ;
            stmt.execute(sql);
            sql = "delete from keywords  where DATEDIFF(curdate(),date(createTime)) > 15 and (number = 0 or number is null)" ;
            stmt.execute(sql);
            logger.info("删除关键词完成");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }

    }

    public void deleteAllKeyword() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "delete from keywords";
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }

    }
    public List<Keyword> findAllKeyword() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Keyword> keywordList = new ArrayList<Keyword>();

        try {
            conn = JdbcUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "select id,keyName,createTime,updateTime, number, status from keywords";


            rs = stmt.executeQuery(sql);

            Keyword keyword = null;

            while (rs.next()) {

                keyword = new Keyword();

                keyword.setId(rs.getInt(1));
                keyword.setKeyName(rs.getString(2));
                keyword.setCreateTime(rs.getTimestamp(3));
                keyword.setUpdateTime(rs.getTimestamp(4));
                keyword.setNumber(rs.getInt(5));
                keyword.setStatus(rs.getString(6));
                keywordList.add(keyword);
            }
            return keywordList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(rs, stmt, conn);
        }
    }


    @Test
    public void TestFindKeywordByName() {
        KeywordDao keywordDao = new KeywordDao();

        Keyword ck = keywordDao.findKeywordByName("ck");

    }


    @Test
    public void TestAddKeyword() {
        KeywordDao keywordDao = new KeywordDao();
/*
        Keyword keyword = new Keyword();

        keyword.setKeyName("蓝");

        ArrayList<Keyword> keywords = new ArrayList<Keyword>();
        keywords.add(keyword);

        keywordDao.addKeyword(keyword);

        keywordDao.addKeywords(keywords);*/

        Keyword lan = keywordDao.findKeywordByName("蓝");

    }

}
