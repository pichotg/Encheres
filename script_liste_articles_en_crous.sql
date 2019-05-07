USE [ENCHERES]
GO

/****** Object:  StoredProcedure [dbo].[encheres_terminees]    Script Date: 07/05/2019 10:15:27 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[liste_articles_en_cours]
AS WITH EN_COURS_CTE (no_article, enchereMax)
AS
(
SELECT no_article,MAX(montant_enchere) as enchereMax FROM ENCHERES GROUP BY no_article
)
SELECT av.*, e.date_enchere, e.montant_enchere FROM ARTICLES_VENDUS av 
left join EN_COURS_CTE ec on ec.no_article = av.no_article
left join ENCHERES e on e.no_article = av.no_article and e.montant_enchere = ec.enchereMax
WHERE av.etat_vente='vec'