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

