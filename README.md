# Rocnikovy-Projekt

Java + JavaFX + CSS

-------------------------

Návod na spustenie: (Len vo WINDOWSe)

Hra sa spúšťa otvorením súboru Hiragana.exe :)

-------------------------

(Nie dobre napísaný) Návod pre tých, ktorí nevedia Hiraganu a chcú testovať - návod ako zmeniť nezrozumiteľné možnosti japonských písmen na možnosti aj s odpoveďami:

V rozbalenom projekte prejdite do zložky src/HiraganaObr, kde sú obrázky, otvorte zložku "Pismenka Aj S Odpovedami NA DEBUG", všetky obrázky zkopírujte, vráťte sa o zložku naspäť, prilepte a zvolte prepísať. Následne bude asi treba v projekte odtrániť zložky "dist" a "build", aby sa mohol kód skompilovať nanovo, s novými obrázkami. Otvorte projekt v NetBeans 8.0.2 a skompilujte. :D (Pôvodné obrázky sú zálohované v "src/HiraganObr/Normalne Pismenka".)
Teraz je pod každou možnosťou aj jej výslovnosť-odpoveď.

-------------------------

Zdroje: 
Obrázky Hiragany: http://www.i2symbol.com/cool-letters/hiragana
Hudba: https://www.youtube.com/watch?v=Wp2oSuh1Hy0
Obrázky Hiragany aj s odpoveďami: http://www.budoshugyosha.com/wp-content/uploads/2013/03/table_hiragana.png

-------------------------

Poznámka:
Na pomalších PC môže byť viditeľný tento problém: po každej zmene Scény sa aplikácia automaticky odfullscreenuje a treba ju znovu nastaviť na fullscreen, čo môže byť pozorovateľné. Toto je oficiálny bug v JaveFX, ktory plánujú v ďalších verizách JavyFX odstrániť ( viď. https://bugs.openjdk.java.net/browse/JDK-8089209 )
