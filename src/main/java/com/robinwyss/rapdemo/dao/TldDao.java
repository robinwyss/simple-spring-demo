package com.robinwyss.rapdemo.dao;

import com.robinwyss.rapdemo.model.TldInfo;
import com.robinwyss.rapdemo.service.LookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class TldDao {

    private static final Logger logger = LoggerFactory.getLogger(TldDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TldDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TldInfo> findCountryByTld(String tld){
        logger.info("Querying for tld {}", tld);
        String query = "SELECT country, tld FROM TLDS WHERE tld like '%"+tld+"';";

        RowMapper<TldInfo> rowMapper = (paramResultSet, paramInt) -> {
            TldInfo tldInfo = new TldInfo();
            tldInfo.setCountry(paramResultSet.getString("country"));
            tldInfo.setTld(paramResultSet.getString("tld"));
            return tldInfo;
        };

        return jdbcTemplate.query(query, rowMapper);
    }
}
