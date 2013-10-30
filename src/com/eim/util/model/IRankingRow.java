/**
 * Title           : $Workfile: IRankingRow.java $
 * Copyright       : EIM (c) 2006
 * Updates         : $Date: 4/28/06 12:10p $
 * By              : $Author: Jpf $
 * Version number  : $Revision: 4 $
 *
 * $History: IRankingRow.java $
 * 
 * *****************  Version 4  *****************
 * User: Jpf          Date: 4/28/06    Time: 12:10p
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * Ajout GICS Sector
 * 
 * *****************  Version 3  *****************
 * User: Jpf          Date: 4/26/06    Time: 10:16a
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * Ajout region group
 * 
 * *****************  Version 2  *****************
 * User: Jpf          Date: 4/19/06    Time: 4:32p
 * Updated in $/Current/Projects/utilities/src/com/eim/util/model
 * Ajout prise en compte de nouveaux indicateurs pour la Ranking Table
 * 
 * *****************  Version 1  *****************
 * User: Jpf          Date: 3/27/06    Time: 2:49p
 * Created in $/Current/Projects/utilities/src/com/eim/util/model
 * Séparation moteur de calcul du moteur de reporting pour la Ranking
 * Table
 */
package com.eim.util.model;


import java.util.Date;
import java.util.List;


/**
 * Interface permettant de représenter un objet RankingRow
 */
@SuppressWarnings("unchecked")
public interface IRankingRow {

	//~ Static fields/initializers ---------------------------------------------

	/**
	 */
	public static final String FUND		 = "Fund";

	/**
	 */
	public static final String INDEX     = "Index";

	/**
	 */
	public static final String PORTFOLIO = "Portfolio";

	//~ Methods ----------------------------------------------------------------

	public abstract Long getID();
	
	/**
	 * @return
	 */
	public abstract double getAnnualCompoundReturn();

	/**
	 * @return  Returns the annualizedStandardDeviation.
	 */
	public abstract double getAnnualizedStandardDeviation();

	/**
	 * @return  Le classification group du fonds
	 */
	public abstract String getClassificationGroup();
	
	/**
	 * @return  L'ID classification group du fonds
	 */
	public abstract Long getClassificationGroupID();	

	/**
	 * @return
	 */
	public abstract double getCurrentDrawdown();

	/**
	 * @return  Date du current return
	 */
	public abstract Date getCurrentReturnDate();

	/**
	 * @return  Dernière perf trouvée dans la db, sans tenir compte de la date de génération du rapport
	 */
	public abstract double getCurrentReturnValue();

	/**
	 * @return  Returns the expectedWorstMonthlyLoss ou Double.NaN si les nombres de mois d'étude est inférieur à 36..
	 */
	public abstract double getExpectedWorstMonthlyLoss();
	public abstract double getExposureGross();
	public abstract double getExposureLong();
	public abstract double getExposureNet();
	public abstract double getExposureShort();

	/**
	 * @return  La nouvelle méthodologie ou une chaine vide
	 */
	public abstract String getFormattedNewMethodology(); // end method getFormattedNewMethodology

	/**
	 * @return  La nouvelle stratégie ou une chaine vide
	 */
	public abstract String getFormattedNewStrategy(); // end method getFormattedNewStrategy

	/**
	 * @return  Le nouveau style ou une chaine vide
	 */
	public abstract String getFormattedNewStyle(); // end method getFormattedNewStyle

	/**
	 * @return  Une string formatée avec les regions
	 */
	public abstract String getFormattedRegions(); // end method getFormattedRegions

	/**
	 * @return  Une string formatée avec les stratégies
	 */
	public abstract String getFormattedStrategies(); // end method getFormattedStrategies
	public abstract Date getInceptionDate();
	public abstract double getKurtosis();

	/**
	 * @return
	 */
	public abstract double getLast12MonthsTotalReturn();

	/**
	 * @return
	 */
	public abstract double getLast18MonthsAnnualizedReturn();

	/**
	 * @return
	 */
	public abstract double getLast18MonthsLargestDrawdown();

	/**
	 * @return
	 */
	public abstract double getLast3MonthsTotalReturn();

	/**
	 * @return
	 */
	public abstract double getLast6MonthsTotalReturn();
	public abstract Date getLastMonthTotalReturnDate();

	/**
	 * @return
	 */
	public abstract double getLastMonthTotalReturnValue();
	public abstract double getLeverage();

	/**
	 * @return  Short wording of the Manager Selection Status
	 */
	public abstract String getManagerSelectionStatus(); // end method getManagerSelectionStatus
	public abstract int getNumberOfMonths();

	/**
	 * @return  Code du portefeuille associé ou null
	 */
	public abstract String getPortfolioCode();

	/**
	 * @return  Company EIM où le portefeuille est enregistré ou null
	 */
	public abstract String getPortfolioCompanySource();
	
	/**
	 * @return Nom du fonds, de l'index ou du portefeuille
	 */
	public abstract String getName();
	
	/**
	 * @return Code de la devise
	 */
	public abstract String getCurrencySymbol();	

	/**
	 * @return Nom court du fonds, de l'index ou du portefeuille
	 */
	public abstract String getShortName();
	
	public abstract double getLargestDrawdown();	
	
	/**
	 * @return  Type d'entité: fund portfolio ou index
	 *
	 * @see     RankingRow.FUND
	 * @see     RankingRow.INDEX
	 * @see     RankingRow.PORTFOLIO
	 */
	public abstract String getEntityType();

	/**
	 * @return
	 */
	public abstract double getSharpeRatio();
	public abstract double getSkewness();

	/**
	 * @return  ID du fonds top level
	 */
	public abstract Long getTopLevelID();
	public abstract Date getTrackRecordStartDate();
	public abstract Date getQueryDate();
	public abstract String getSecurityType();

	public abstract int getMaximumTimeToRecovery();
	
	/**
	 * @return  Une liste d'objets DST TimeSeriesValues contenant les 4 derniers YTD
	 */
	public abstract List getYTD();

	/**
	 * @return  YTD
	 */
	public abstract double getYTDTotalReturn();


	/**
	 * 
	 * @return Nom long du fonds top level
	 */
	public abstract String getTopLevelName();

	/**
	 * 
	 * @return Devise du fonds top level
	 */
	public abstract String getTopLevelCurrencySymbol();	

	/**
	 * 
	 * @return Nom court du fonds top level
	 */
	public abstract String getTopLevelShortName();
	
	/**
	 * 
	 * @return Montant total sous gestion du fonds
	 */
	public abstract double getAUMTotal();
	
	public abstract double getLast36MonthsAnnualizedReturn();
	
	public abstract double getLast60MonthsAnnualizedReturn();
	
	public abstract double getTotalReturn();
	
	/**
	 * 
	 * @return Id du region group associé, celui qui prédomine
	 */
	public abstract Long getNewMainRegionsGroupID();
	
	/**
	 * 
	 * @return ID du GICS Industry sector associé, celui qui prédomine
	 */
	public abstract Long getNewMainGICSIndustrySectorID();	
	
	
	/**
	 * 
	 * @return Capacity Status du fonds, le fonds est il ouvert aux investisseurs 
	 */
	public abstract String getFundCapacityStatus();
	
	/**
	 * 
	 * @return Capacity Status du fonds, le fonds est il ouvert pour EIM
	 */
	public abstract String getEIMCapacityStatus();
	
	/**
	 * 
	 * @return Montant qu'EIM peut encore investir dans le fonds
	 */
	public abstract double getEIMRemainingCapacity();
	
	/**
	 * 
	 * @return Total des investissements EIM dans le fonds ou le portefeuilles
	 */
	public abstract double getEIMInvestments();	
} // end interface IRankingRow
