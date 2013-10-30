package com.eim.util.livelink;

import java.util.EnumSet;


public enum LivelinkDocumentType {
	
	ARTICLES_OF_ASSOCIATION("ARTICLES OF ASSOCIATION","[AoA]", true, false),
	COLLATERAL_MANAGEMENT_AND_RELATED_SERVICES_AGREEMENT("COLLATERAL MANAGEMENT AND RELATED SERVICES AGREEMENT","[CMRSA]", true, false),
	CONSTITUTIVE_DOCUMENT("CONSTITUTIVE DOCUMENT","[CD]", true, false),
	CORPORATE_ACTION("CORPORATE ACTION","[CA]", true, false),
	CORPORATE_ADMINISTRATOR_AGREEMENT("CORPORATE ADMINISTRATOR AGREEMENT","[CAA]", true, false),
	CURRENCY_HEDGE_ADMINISTRATION_AGREEMENT("CURRENCY HEDGE ADMINISTRATION AGREEMENT","[CHAA]", true, false),
	CURRENCY_HEDGE_AGREEMENT("CURRENCY HEDGE AGREEMENT","[CHA]", true, false),
	CUSTODY_AGREEMENT("CUSTODY AGREEMENT","[CustA]", true, false),
	DUE_DILIGENCE_GLOBAL("DUE DILIGENCE (GLOBAL)","[DDQuest]", true, false),
	DUE_DILIGENCE_MANAGER_BACKGROUND_CHECK("DUE DILIGENCE (MANAGER BACKGROUND CHECK)","[DDMgrCheck]", true, false),
	DUE_DILIGENCE_ONSITE_OPERATIONAL_UPDATE("DUE DILIGENCE (ONSITE OPERATIONAL UPDATE)","[DDOOU]", true, false),
	DUE_DILIGENCE_OPERATIONAL("DUE DILIGENCE (OPERATIONAL)","[DDO]", true, false),
	DUE_DILIGENCE_QUALITATIVE("DUE DILIGENCE (QUALITATIVE)","[EIM DDQual]", true, false),
	DUE_DILIGENCE_QUANT("DUE DILIGENCE (QUANT)","[DDQuant]", true, false),
	DUE_DILIGENCE_QUESTIONNAIRE("DUE DILIGENCE (QUESTIONNAIRE)","[DDQuest]", true, false),
	FINANCIAL_STATEMENT("FINANCIAL STATEMENT","[FS]", true, false),
	FORM_ADV("FORM ADV","[FA]", true, false),
	FULL_ANALYTICAL_REPORT("FULL ANALYTICAL REPORT","[FAR]", true, false),
	FUND_ADMINISTRATOR_AGREEMENT("FUND ADMINISTRATOR AGREEMENT","[FAA]", true, false),
	FUND_LIQUIDITY_SHEET("FUND LIQUIDITY SHEET","[FLS]", true, false),
	FUND_PROXIES("FUND PROXIES","[FP]", true, false),
	HIGHLIGHT_REPORT_RESEARCH("HIGHLIGHT REPORT (RESEARCH)","[HRR]", true, false),
	INVESTMENT_LETTER("INVESTMENT LETTER","[IL]", true, false),
	KYC_PROCEDURE("KYC PROCEDURE","[KP]", true, false),
	LIQUIDITY_SERVICES_AGREEMENT("LIQUIDITY SERVICES AGREEMENT","[LSA]", true, false),
	MANAGER_INFO_AND_CONTRACTS("MANAGER INFO. AND CONTRACTS","[MIC]", true, false),
	MARKETING_MATERIAL("MARKETING MATERIAL","[MM]", true, false),
	OFFERING_MEMORANDUM("OFFERING MEMORANDUM","[OM]", true, false),
	ON_SITE_VISIT("ON SITE VISIT","[OSV]", true, false),
	OTHER_HIERARCHY_RELATED_DOCUMENT("OTHER HIERARCHY RELATED DOCUMENT","[OHRD]", true, false),
	PRIME_BROKER_MARGIN_ACCOUNT_AGREEMENT("PRIME BROKER MARGIN ACCOUNT AGREEMENT","[PBMAA]", true, false),
	PRIME_BROKERAGE_AGREEMENT("PRIME BROKERAGE AGREEMENT","[PBA]", true, false),
	QUANTITATIVE("QUANTITATIVE","[QUANT]", true, false),
	REDEMPTION_FORM("REDEMPTION FORM","[RF]", true, false),
	REQUEST_FOR_PROPOSAL("REQUEST FOR PROPOSAL","[RFP]", true, false),
	RISK_ANALYSIS("RISK ANALYSIS","[RA] [MKT]", true, false),
	RISK_MONITOR_AGREEMENT("RISK MONITOR AGREEMENT","[RMA]", true, false),
	SHORT_ANALYTICAL_REPORT("SHORT ANALYTICAL REPORT","[SAR]", true, false),
	SIMPLIFIED_PROSPECTUS("SIMPLIFIED PROSPECTUS","[SP]", true, false),
	SUBSCRIPTION_FORM("SUBSCRIPTION FORM","[SF]", true, false),
	SUBSCRIPTION_FORM_FOR_ADDITIONAL_INVESTMENT("SUBSCRIPTION FORM FOR ADDITIONAL INVESTMENT","[SFAI]", true, false),
	SUPPLEMENTAL_ADMINISTRATION_AGREEMENT("SUPPLEMENTAL ADMINISTRATION AGREEMENT","[CAA]", true, false),
	TRADING_ADVISER_AGREEMENT("TRADING ADVISER AGREEMENT","[TAA]", true, false),
	TRADING_ADVISER_SUPPLEMENT("TRADING ADVISER SUPPLEMENT","[TAS]", true, false),
	TRANSFER_FORM("TRANSFER FORM","[TF]", true, false),
	UNKNOWN("UNKNOWN","[UNK]", true, false),

	AUDITED_FINANCIALS			("AUDITED FINANCIALS","[AF]", true, false),
	AUDIT_REPORT_CHECKLIST		("AUDIT REPORT CHECKLIST"		, "[ARC]"  , true, false), 

	BUY_WATCH_SELL_NOTES		("BUY/WATCH"			, "[BWSN]"  , true, false), 
	CORRELATION_REPORT			("CORRELATION REPORT"			, "[CR]"  , true, false),
	MANAGER_FACT_SHEET			("MANAGER FACT SHEET"			, "[MFS]" , true, false),
	MONTHLY_STAT				("MONTHLY STAT"					, "[MS]"  , true, false),
	MANAGER_REPORT				("MANAGER REPORT"				, "[MRep]", true, false),
	MONTHLY_REPORT_CLIENT		("MONTHLY REPORT (CLIENT)"		, "[MRC]" , true, false),
	MONTHLY_REPORT_RESEARCH		("MONTHLY REPORT (RESEARCH)"	, "[MRR]" , true, true),
	MONTHLY_REPORT_TEMPLATE		("MONTHLY REPORT (TEMPLATE)"	, "[MRT]" , true, true),
	QUALITATIVE_SCORING_CARDS	("QUALITATIVE SCORING CARDS"	, "[QSC]" , true, false),
	QUARTERLY_REPORT_CLIENT		("QUARTERLY REPORT (CLIENT)"	, "[QRC]" , true, false),
	QUARTERLY_REPORT_RESEARCH	("QUARTERLY REPORT (RESEARCH)"	, "[QRR]" , true, false),
	HIGHLIGHT_REPORT_CLIENT		("HIGHLIGHT REPORT (CLIENT)"	, "[HRC]" , true, false),
	NONE						(""								, "" 	  , true, false),
	MANAGER_QUARTERLY_REPORT    ("MANAGER QUARTERLY REPORT","[MQRep]",true,false),
	LUMX_RISK_REPORT            ("LUMX RISK REPORT","[LRR]",true,false),
	MANAGER_ANNUAL_REPORT       ("MANAGER ANNUAL REPORT","[MARep]", true, false),
	MANAGER_RISK_REPORT         ("MANAGER RISK REPORT","[MRRep]", true, false),
	MANAGER_EXPOSURE_REPORT     ("MANAGER EXPOSURE REPORT","[MERep]",true, false),
	MANAGER_COMMENT 			("MANAGER COMMENT","[MC]", true, false),
	MANAGER_PORTFOLIO_DETAILS   ("MANAGER PORTFOLIO DETAILS","[MPD]",true, false),
	BREACH                      ("BREACH","[B]",true, false),
	ONE_PAGE                    ("ONE PAGE","[OP]",true, false),
	SIDE_LETTER					("SIDE LETTER","[SL]",true,false),
	INVESTMENT_GUIDELINES_FLAG	("INVESTMENT GUIDELINES & FLAGGING","[RA] [RG]",true,false),
	COUNTERPARTY_RISK			("COUNTERPARTY RISK","[RA] [CPTY]",true,false),
	ASSET_LIABILITY_MANAGEMENT	("ASSET LIABILITY MANAGEMENT","[RA] [ALM]",true,false),
	QUARTERLY_RISK_ANALYSIS     ("QUARTERLY RISK ANALYSIS","[QRA]",true,false);

	private String fullName;
	private String prefix;
	private boolean stackable;
	private boolean advancedVersioning;
	private LivelinkDocumentType(String fullName, String prefix, boolean stackable, boolean advancedVersioning){
		this.fullName = fullName;
		this.prefix = prefix;
		this.stackable = stackable;
		this.advancedVersioning = advancedVersioning;
	}
	
    /**
     * @return the fullName
     */
    public String getFullName() {
    	return fullName;
    }
	
    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
    	this.fullName = fullName;
    }
	
    /**
     * @return the prefix
     */
    public String getPrefix() {
    	return prefix;
    }
	
    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
    	this.prefix = prefix;
    }
	
    /**
     * @return the stackable
     */
    public boolean isStackable() {
    	return stackable;
    }
	
    /**
     * @param stackable the stackable to set
     */
    public void setStackable(boolean stackable) {
    	this.stackable = stackable;
    }
    
    public static final LivelinkDocumentType getType(String type){
    	for (LivelinkDocumentType llType : EnumSet.allOf(LivelinkDocumentType.class)) {
	        if(llType.fullName.equals(type)){
	        	return llType;
	        }
        }
    	return null;
    }

	
    /**
     * @return the advancedVersioning
     */
    public boolean isAdvancedVersioning() {
    	return advancedVersioning;
    }

	
    /**
     * @param advancedVersioning the advancedVersioning to set
     */
    public void setAdvancedVersioning(boolean advancedVersioning) {
    	this.advancedVersioning = advancedVersioning;
    }
}
