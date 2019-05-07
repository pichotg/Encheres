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

