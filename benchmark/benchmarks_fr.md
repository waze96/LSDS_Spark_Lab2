## BENCHMARK FRENCH Spark-based TwitterFilter

	[*] Run time from inside 'controller' LOG file.
	[*] FR:	INFO total process run time: 220 seconds


I execute three times to see the diferences between remove the Hastags or not, because I think that is a significant symbol in twitter, so the results differ a little bit. The last experiment was removing StopWords

#### TOP 10 BiGrams French without Hashtag

	1619 la france
	1292 de la
	997 ce soir
	870 de leurovision
	650 je suis
	636 la chanson
	618 l eurovision
	617 pour la
	377 cette anne
	375 y a

#### TOP 10 BiGrams French with Hashtag

	1523 la france
	1292 de la
	997 ce soir
	650 je suis
	644 de leurovision
	634 la chanson
	617 pour la
	482 l #eurovision
	377 cette anne
	375 y a

#### TOP 10 BiGrams French without STOPWORDS

	377 cette anne
	353 #eurovision #eurovision2018
	318 madame monsieur
	286 une chanson
	286 france #eurovision
	280 cest pas
	261 on est
	241 pour france
	235 eurovision 2018
	233 leurovision cest
