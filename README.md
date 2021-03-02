# BENCHMARKS 

## Spark-based TwitterFilter
[!!] Run time from 'controller' LOG file.
	[!!] ES:	INFO total process run time: 230 seconds
	[!!] EN:	INFO total process run time: 230 seconds
	[!!] FR:	INFO total process run time: 232 seconds

## Most popular bi-grams
I execute this command to get recursively all the output files in local (Change the path):

	aws s3 cp --recursive s3://bucketName/output/run4_en_BIGRAMS/ /tmp/en_BIGRAMS

I used this UNIX commands inside the /tmp/en_BIGRAMS to get the top 10 BIGRAMS.
This commands changes the order of the line, remove chars, sorts by frequency and shows the TOP 10

	cat part-* | awk 'BEGIN {FS=","; OFS=" "}{print $NF,$1}' | tr -d '()' | sort -n -r | head


[!!] I execute two times to see the diferences between remove the # or not
	 Because I think that # is a significant symbol in twitter, so the results differ a little bit.

#### TOP 10 BiGrams Spanish without HASHTAGS

	4519 de eurovision
	3451 de la
	3343 en eurovision
	3117 eurovision finaleurovision
	2508 que no
	2507 la cancin
	2204 en el
	2043 lo que
	1850 a la
	1835 en la

#### TOP 10 BiGrams Spanish with HASHTAGS

	3451 de la
	3070 #eurovision #finaleurovision
	2520 de #eurovision
	2508 que no
	2507 la cancin
	2204 en el
	2103 en #eurovision
	2043 lo que
	1999 de eurovision
	1850 a la
	
#### TOP 10 BiGrams Spanish without STOPWORDS

	3071 #eurovision #finaleurovision
	1625 este ao
	1291 #finaleurovision #eurovision
	1179 amaia alfred
	1106 #eurovision #eurovision2018
	1032 los memes
	884 ao viene
	880 repblica checa
	860 #eurovision #allaboard
	831 amaiaot2017 alfredot2017



#### TOP 10 BiGrams English without HASHTAGS

	7300 the eurovision
	5954 this is
	5824 of the
	5282 in the
	4950 eurovision is
	4406 for the
	3991 eurovision song
	3633 for eurovision
	3364 song contest
	3139 i love

#### TOP 10 BiGrams English with HASHTAGS

	5954 this is
	5824 of the
	5282 in the
	5014 the eurovision
	4406 for the
	3521 eurovision is
	3368 eurovision song
	3354 song contest
	3139 i love
	2970 is the

#### TOP 10 BiGrams English without STOPWORDS

	3394 eurovision song
	3364 song contest
	2384 #eurovision #eurovision2018
	2344 watching eurovision
	1981 next year
	1938 #eurovision #esc2018
	1914 eurovision 2018
	1885 watch eurovision
	1875 #eurovision #allaboard
	1586 it #eurovision



#### TOP 10 BiGrams French without HASHTAGS

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


#### TOP 10 BiGrams French with HASHTAGS

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
