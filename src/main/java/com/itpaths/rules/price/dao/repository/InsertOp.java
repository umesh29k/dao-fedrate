package com.itpaths.rules.price.dao.repository;

import com.itpaths.rules.price.dao.model.Calndr;
import com.itpaths.rules.price.dao.model.TtFormula;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class InsertOp {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(4, calndr.getCalndrDayInYr())
                .setParameter(5, calndr.getCalndrHoliday())
                .setParameter(6, calndr.getCalndrWkNo())
                .setParameter(7, calndr.getCalndrHighSeasn())
                .setParameter(8, calndr.getOperBadgeId())
                .setParameter(9, calndr.getOperId())
                .setParameter(10, calndr.getTimeStamp())
                .executeUpdate();
    }

    @Transactional
    public void insertTtformula(TtFormula ttFormula) {
        entityManager.createNativeQuery("INSERT INTO Calndr (FRML_ID,STS_CD,VLDTY_DATE,DSCRPN_D,DSCRPN_E,DSCRPN_F," +
                "DSCRPN_G,PRMTR_NO,FRML_STRNG,OPER_ID,OPER_BADGE_ID,TIME_STAMP,IBIS_AWD_FLG) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .setParameter(1, ttFormula.getFrmlId())
                .setParameter(2, ttFormula.getStsCd())
                .setParameter(3, ttFormula.getVldtyDate())
                .setParameter(4, ttFormula.getDscrpnD())
                .setParameter(5, ttFormula.getDscrpnE())
                .setParameter(6, ttFormula.getDscrpnF())
                .setParameter(7, ttFormula.getDscrpnG())
                .setParameter(8, ttFormula.getPrmtrNo())
                .setParameter(9, ttFormula.getFrmlStrng())
                .setParameter(10, ttFormula.getOperId())
                .setParameter(11, ttFormula.getOperBadgeId())
                .setParameter(12, ttFormula.getTimeStamp())
                .setParameter(13, ttFormula.getIbisAwdFlg())
                .executeUpdate();
    }

    /*@Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(3, calndr.getCalndrDayInYr())
                .setParameter(3, calndr.getCalndrHoliday())
                .setParameter(3, calndr.getCalndrWkNo())
                .setParameter(3, calndr.getCalndrHighSeasn())
                .setParameter(3, calndr.getOperBadgeId())
                .setParameter(3, calndr.getOperId())
                .setParameter(3, calndr.getTimeStamp())
                .executeUpdate();
    }

    @Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(3, calndr.getCalndrDayInYr())
                .setParameter(3, calndr.getCalndrHoliday())
                .setParameter(3, calndr.getCalndrWkNo())
                .setParameter(3, calndr.getCalndrHighSeasn())
                .setParameter(3, calndr.getOperBadgeId())
                .setParameter(3, calndr.getOperId())
                .setParameter(3, calndr.getTimeStamp())
                .executeUpdate();
    }

    @Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(3, calndr.getCalndrDayInYr())
                .setParameter(3, calndr.getCalndrHoliday())
                .setParameter(3, calndr.getCalndrWkNo())
                .setParameter(3, calndr.getCalndrHighSeasn())
                .setParameter(3, calndr.getOperBadgeId())
                .setParameter(3, calndr.getOperId())
                .setParameter(3, calndr.getTimeStamp())
                .executeUpdate();
    }

    @Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(3, calndr.getCalndrDayInYr())
                .setParameter(3, calndr.getCalndrHoliday())
                .setParameter(3, calndr.getCalndrWkNo())
                .setParameter(3, calndr.getCalndrHighSeasn())
                .setParameter(3, calndr.getOperBadgeId())
                .setParameter(3, calndr.getOperId())
                .setParameter(3, calndr.getTimeStamp())
                .executeUpdate();
    }

    @Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(3, calndr.getCalndrDayInYr())
                .setParameter(3, calndr.getCalndrHoliday())
                .setParameter(3, calndr.getCalndrWkNo())
                .setParameter(3, calndr.getCalndrHighSeasn())
                .setParameter(3, calndr.getOperBadgeId())
                .setParameter(3, calndr.getOperId())
                .setParameter(3, calndr.getTimeStamp())
                .executeUpdate();
    }

    @Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(3, calndr.getCalndrDayInYr())
                .setParameter(3, calndr.getCalndrHoliday())
                .setParameter(3, calndr.getCalndrWkNo())
                .setParameter(3, calndr.getCalndrHighSeasn())
                .setParameter(3, calndr.getOperBadgeId())
                .setParameter(3, calndr.getOperId())
                .setParameter(3, calndr.getTimeStamp())
                .executeUpdate();
    }

    @Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(3, calndr.getCalndrDayInYr())
                .setParameter(3, calndr.getCalndrHoliday())
                .setParameter(3, calndr.getCalndrWkNo())
                .setParameter(3, calndr.getCalndrHighSeasn())
                .setParameter(3, calndr.getOperBadgeId())
                .setParameter(3, calndr.getOperId())
                .setParameter(3, calndr.getTimeStamp())
                .executeUpdate();
    }

    @Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(3, calndr.getCalndrDayInYr())
                .setParameter(3, calndr.getCalndrHoliday())
                .setParameter(3, calndr.getCalndrWkNo())
                .setParameter(3, calndr.getCalndrHighSeasn())
                .setParameter(3, calndr.getOperBadgeId())
                .setParameter(3, calndr.getOperId())
                .setParameter(3, calndr.getTimeStamp())
                .executeUpdate();
    }

    @Transactional
    public void insertWithQuery(Calndr calndr) {
        entityManager.createNativeQuery("INSERT INTO Calndr (CALNDR_DATE,STS_CD,CALNDR_DAY_IN_WK,CALNDR_DAY_IN_YR," +
                "CALNDR_HOLIDAY,CALNDR_WK_NO,CALNDR_HIGH_SEASN,OPER_BADGE_ID,OPER_ID,TIME_STAMP) VALUES (?,?,?,?,?,?," +
                "?,?,?,?)")
                .setParameter(1, calndr.getCalndrDate())
                .setParameter(2, calndr.getStsCd())
                .setParameter(3, calndr.getCalndrDayInWk())
                .setParameter(3, calndr.getCalndrDayInYr())
                .setParameter(3, calndr.getCalndrHoliday())
                .setParameter(3, calndr.getCalndrWkNo())
                .setParameter(3, calndr.getCalndrHighSeasn())
                .setParameter(3, calndr.getOperBadgeId())
                .setParameter(3, calndr.getOperId())
                .setParameter(3, calndr.getTimeStamp())b
                .executeUpdate();
    }
*/
}
