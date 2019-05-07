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
GO


USE [ENCHERES]
GO

/****** Object:  StoredProcedure [dbo].[encheres_terminees]    Script Date: 07/05/2019 10:15:27 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[liste_filtre_categorie_nom_deconnecte] @no_categorie integer, @contient varchar (30)
AS 
IF @no_categorie != 5 AND @no_categorie is not null

	WITH EN_COURS_CTE (no_article, enchereMax)
	AS
	(
	SELECT no_article,MAX(montant_enchere) as enchereMax FROM ENCHERES GROUP BY no_article
	)

	SELECT av.*, e.date_enchere, e.montant_enchere FROM ARTICLES_VENDUS av 
	left join EN_COURS_CTE ec on ec.no_article = av.no_article
	left join ENCHERES e on e.no_article = av.no_article and e.montant_enchere = ec.enchereMax
	WHERE av.etat_vente='vec' AND av.no_categorie = @no_categorie AND av.nom_article like @contient
	order by av.date_fin_encheres ASC;

ELSE

	WITH EN_COURS_CTE (no_article, enchereMax)
	AS
	(
		SELECT no_article,MAX(montant_enchere) as enchereMax FROM ENCHERES GROUP BY no_article
	)

	SELECT av.*, e.date_enchere, e.montant_enchere FROM ARTICLES_VENDUS av 
	left join EN_COURS_CTE ec on ec.no_article = av.no_article
	left join ENCHERES e on e.no_article = av.no_article and e.montant_enchere = ec.enchereMax
	WHERE av.etat_vente='vec' AND av.nom_article like '%'+@contient+'%'
	order by av.date_fin_encheres ASC;
	GO

	USE [ENCHERES]
GO

/****** Object:  StoredProcedure [dbo].[encheres_a_debuter]    Script Date: 07/05/2019 10:14:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[encheres_a_debuter]
AS
DECLARE c_articles CURSOR FOR SELECT no_article from ARTICLES_VENDUS WHERE etat_vente = 'vnd' AND date_debut_encheres < GETDATE() FOR UPDATE OF etat_vente;
DECLARE  @v_article INT;
OPEN c_articles FETCH c_articles INTO @v_article
WHILE @@FETCH_STATUS = 0
BEGIN
	UPDATE [ENCHERES].[dbo].[ARTICLES_VENDUS] 
	SET 
		etat_vente = 'vec'
	WHERE no_article = @v_article;
	FETCH NEXT FROM c_articles INTO @v_article;
END;
CLOSE c_articles;
DEALLOCATE c_articles;
GO



USE [ENCHERES]
GO

/****** Object:  StoredProcedure [dbo].[encheres_terminees]    Script Date: 07/05/2019 10:15:27 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[encheres_terminees]
AS
DECLARE c_articles CURSOR FOR SELECT no_article from ARTICLES_VENDUS WHERE etat_vente = 'vec' AND date_fin_encheres < GETDATE() FOR UPDATE OF etat_vente, prix_vente;
DECLARE  @v_article INT;
OPEN c_articles FETCH c_articles INTO @v_article
WHILE @@FETCH_STATUS = 0
BEGIN
	UPDATE [ENCHERES].[dbo].[ARTICLES_VENDUS] 
	SET 
		etat_vente = 'vet',
		prix_vente = (SELECT MAX(montant_enchere) AS enchereMax FROM ENCHERES WHERE no_article = @v_article)
	WHERE no_article = @v_article;
	FETCH NEXT FROM c_articles INTO @v_article;
END;
CLOSE c_articles;
DEALLOCATE c_articles;
GO


