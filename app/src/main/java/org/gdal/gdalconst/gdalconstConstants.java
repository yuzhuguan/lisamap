/*     */ package org.gdal.gdalconst;
/*     */ 
/*     */ public abstract interface gdalconstConstants
/*     */ {
/*  12 */   public static final int GDT_Unknown = gdalconstJNI.GDT_Unknown_get();
/*  13 */   public static final int GDT_Byte = gdalconstJNI.GDT_Byte_get();
/*  14 */   public static final int GDT_UInt16 = gdalconstJNI.GDT_UInt16_get();
/*  15 */   public static final int GDT_Int16 = gdalconstJNI.GDT_Int16_get();
/*  16 */   public static final int GDT_UInt32 = gdalconstJNI.GDT_UInt32_get();
/*  17 */   public static final int GDT_Int32 = gdalconstJNI.GDT_Int32_get();
/*  18 */   public static final int GDT_Float32 = gdalconstJNI.GDT_Float32_get();
/*  19 */   public static final int GDT_Float64 = gdalconstJNI.GDT_Float64_get();
/*  20 */   public static final int GDT_CInt16 = gdalconstJNI.GDT_CInt16_get();
/*  21 */   public static final int GDT_CInt32 = gdalconstJNI.GDT_CInt32_get();
/*  22 */   public static final int GDT_CFloat32 = gdalconstJNI.GDT_CFloat32_get();
/*  23 */   public static final int GDT_CFloat64 = gdalconstJNI.GDT_CFloat64_get();
/*  24 */   public static final int GDT_TypeCount = gdalconstJNI.GDT_TypeCount_get();
/*  25 */   public static final int GA_ReadOnly = gdalconstJNI.GA_ReadOnly_get();
/*  26 */   public static final int GA_Update = gdalconstJNI.GA_Update_get();
/*  27 */   public static final int GF_Read = gdalconstJNI.GF_Read_get();
/*  28 */   public static final int GF_Write = gdalconstJNI.GF_Write_get();
/*  29 */   public static final int GCI_Undefined = gdalconstJNI.GCI_Undefined_get();
/*  30 */   public static final int GCI_GrayIndex = gdalconstJNI.GCI_GrayIndex_get();
/*  31 */   public static final int GCI_PaletteIndex = gdalconstJNI.GCI_PaletteIndex_get();
/*  32 */   public static final int GCI_RedBand = gdalconstJNI.GCI_RedBand_get();
/*  33 */   public static final int GCI_GreenBand = gdalconstJNI.GCI_GreenBand_get();
/*  34 */   public static final int GCI_BlueBand = gdalconstJNI.GCI_BlueBand_get();
/*  35 */   public static final int GCI_AlphaBand = gdalconstJNI.GCI_AlphaBand_get();
/*  36 */   public static final int GCI_HueBand = gdalconstJNI.GCI_HueBand_get();
/*  37 */   public static final int GCI_SaturationBand = gdalconstJNI.GCI_SaturationBand_get();
/*  38 */   public static final int GCI_LightnessBand = gdalconstJNI.GCI_LightnessBand_get();
/*  39 */   public static final int GCI_CyanBand = gdalconstJNI.GCI_CyanBand_get();
/*  40 */   public static final int GCI_MagentaBand = gdalconstJNI.GCI_MagentaBand_get();
/*  41 */   public static final int GCI_YellowBand = gdalconstJNI.GCI_YellowBand_get();
/*  42 */   public static final int GCI_BlackBand = gdalconstJNI.GCI_BlackBand_get();
/*  43 */   public static final int GCI_YCbCr_YBand = gdalconstJNI.GCI_YCbCr_YBand_get();
/*  44 */   public static final int GCI_YCbCr_CrBand = gdalconstJNI.GCI_YCbCr_CrBand_get();
/*  45 */   public static final int GCI_YCbCr_CbBand = gdalconstJNI.GCI_YCbCr_CbBand_get();
/*  46 */   public static final int GRA_NearestNeighbour = gdalconstJNI.GRA_NearestNeighbour_get();
/*  47 */   public static final int GRA_Bilinear = gdalconstJNI.GRA_Bilinear_get();
/*  48 */   public static final int GRA_Cubic = gdalconstJNI.GRA_Cubic_get();
/*  49 */   public static final int GRA_CubicSpline = gdalconstJNI.GRA_CubicSpline_get();
/*  50 */   public static final int GRA_Lanczos = gdalconstJNI.GRA_Lanczos_get();
/*  51 */   public static final int GPI_Gray = gdalconstJNI.GPI_Gray_get();
/*  52 */   public static final int GPI_RGB = gdalconstJNI.GPI_RGB_get();
/*  53 */   public static final int GPI_CMYK = gdalconstJNI.GPI_CMYK_get();
/*  54 */   public static final int GPI_HLS = gdalconstJNI.GPI_HLS_get();
/*  55 */   public static final int CXT_Element = gdalconstJNI.CXT_Element_get();
/*  56 */   public static final int CXT_Text = gdalconstJNI.CXT_Text_get();
/*  57 */   public static final int CXT_Attribute = gdalconstJNI.CXT_Attribute_get();
/*  58 */   public static final int CXT_Comment = gdalconstJNI.CXT_Comment_get();
/*  59 */   public static final int CXT_Literal = gdalconstJNI.CXT_Literal_get();
/*  60 */   public static final int CE_None = gdalconstJNI.CE_None_get();
/*  61 */   public static final int CE_Debug = gdalconstJNI.CE_Debug_get();
/*  62 */   public static final int CE_Warning = gdalconstJNI.CE_Warning_get();
/*  63 */   public static final int CE_Failure = gdalconstJNI.CE_Failure_get();
/*  64 */   public static final int CE_Fatal = gdalconstJNI.CE_Fatal_get();
/*  65 */   public static final int CPLE_None = gdalconstJNI.CPLE_None_get();
/*  66 */   public static final int CPLE_AppDefined = gdalconstJNI.CPLE_AppDefined_get();
/*  67 */   public static final int CPLE_OutOfMemory = gdalconstJNI.CPLE_OutOfMemory_get();
/*  68 */   public static final int CPLE_FileIO = gdalconstJNI.CPLE_FileIO_get();
/*  69 */   public static final int CPLE_OpenFailed = gdalconstJNI.CPLE_OpenFailed_get();
/*  70 */   public static final int CPLE_IllegalArg = gdalconstJNI.CPLE_IllegalArg_get();
/*  71 */   public static final int CPLE_NotSupported = gdalconstJNI.CPLE_NotSupported_get();
/*  72 */   public static final int CPLE_AssertionFailed = gdalconstJNI.CPLE_AssertionFailed_get();
/*  73 */   public static final int CPLE_NoWriteAccess = gdalconstJNI.CPLE_NoWriteAccess_get();
/*  74 */   public static final int CPLE_UserInterrupt = gdalconstJNI.CPLE_UserInterrupt_get();
/*  75 */   public static final String DMD_LONGNAME = gdalconstJNI.DMD_LONGNAME_get();
/*  76 */   public static final String DMD_HELPTOPIC = gdalconstJNI.DMD_HELPTOPIC_get();
/*  77 */   public static final String DMD_MIMETYPE = gdalconstJNI.DMD_MIMETYPE_get();
/*  78 */   public static final String DMD_EXTENSION = gdalconstJNI.DMD_EXTENSION_get();
/*  79 */   public static final String DMD_CREATIONOPTIONLIST = gdalconstJNI.DMD_CREATIONOPTIONLIST_get();
/*  80 */   public static final String DMD_CREATIONDATATYPES = gdalconstJNI.DMD_CREATIONDATATYPES_get();
/*  81 */   public static final String DCAP_CREATE = gdalconstJNI.DCAP_CREATE_get();
/*  82 */   public static final String DCAP_CREATECOPY = gdalconstJNI.DCAP_CREATECOPY_get();
/*  83 */   public static final String DCAP_VIRTUALIO = gdalconstJNI.DCAP_VIRTUALIO_get();
/*  84 */   public static final int CPLES_BackslashQuotable = gdalconstJNI.CPLES_BackslashQuotable_get();
/*  85 */   public static final int CPLES_XML = gdalconstJNI.CPLES_XML_get();
/*  86 */   public static final int CPLES_URL = gdalconstJNI.CPLES_URL_get();
/*  87 */   public static final int CPLES_SQL = gdalconstJNI.CPLES_SQL_get();
/*  88 */   public static final int CPLES_CSV = gdalconstJNI.CPLES_CSV_get();
/*  89 */   public static final int GFT_Integer = gdalconstJNI.GFT_Integer_get();
/*  90 */   public static final int GFT_Real = gdalconstJNI.GFT_Real_get();
/*  91 */   public static final int GFT_String = gdalconstJNI.GFT_String_get();
/*  92 */   public static final int GFU_Generic = gdalconstJNI.GFU_Generic_get();
/*  93 */   public static final int GFU_PixelCount = gdalconstJNI.GFU_PixelCount_get();
/*  94 */   public static final int GFU_Name = gdalconstJNI.GFU_Name_get();
/*  95 */   public static final int GFU_Min = gdalconstJNI.GFU_Min_get();
/*  96 */   public static final int GFU_Max = gdalconstJNI.GFU_Max_get();
/*  97 */   public static final int GFU_MinMax = gdalconstJNI.GFU_MinMax_get();
/*  98 */   public static final int GFU_Red = gdalconstJNI.GFU_Red_get();
/*  99 */   public static final int GFU_Green = gdalconstJNI.GFU_Green_get();
/* 100 */   public static final int GFU_Blue = gdalconstJNI.GFU_Blue_get();
/* 101 */   public static final int GFU_Alpha = gdalconstJNI.GFU_Alpha_get();
/* 102 */   public static final int GFU_RedMin = gdalconstJNI.GFU_RedMin_get();
/* 103 */   public static final int GFU_GreenMin = gdalconstJNI.GFU_GreenMin_get();
/* 104 */   public static final int GFU_BlueMin = gdalconstJNI.GFU_BlueMin_get();
/* 105 */   public static final int GFU_AlphaMin = gdalconstJNI.GFU_AlphaMin_get();
/* 106 */   public static final int GFU_RedMax = gdalconstJNI.GFU_RedMax_get();
/* 107 */   public static final int GFU_GreenMax = gdalconstJNI.GFU_GreenMax_get();
/* 108 */   public static final int GFU_BlueMax = gdalconstJNI.GFU_BlueMax_get();
/* 109 */   public static final int GFU_AlphaMax = gdalconstJNI.GFU_AlphaMax_get();
/* 110 */   public static final int GFU_MaxCount = gdalconstJNI.GFU_MaxCount_get();
/* 111 */   public static final int GMF_ALL_VALID = gdalconstJNI.GMF_ALL_VALID_get();
/* 112 */   public static final int GMF_PER_DATASET = gdalconstJNI.GMF_PER_DATASET_get();
/* 113 */   public static final int GMF_ALPHA = gdalconstJNI.GMF_ALPHA_get();
/* 114 */   public static final int GMF_NODATA = gdalconstJNI.GMF_NODATA_get();
/* 115 */   public static final int GARIO_PENDING = gdalconstJNI.GARIO_PENDING_get();
/* 116 */   public static final int GARIO_UPDATE = gdalconstJNI.GARIO_UPDATE_get();
/* 117 */   public static final int GARIO_ERROR = gdalconstJNI.GARIO_ERROR_get();
/* 118 */   public static final int GARIO_COMPLETE = gdalconstJNI.GARIO_COMPLETE_get();
/*     */ }

/* Location:           E:\temp\1117\gdal.jar
 * Qualified Name:     org.gdal.gdalconst.gdalconstConstants
 * JD-Core Version:    0.5.4
 */