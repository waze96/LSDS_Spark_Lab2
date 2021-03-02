# BENCHMARKS 

## GitHub Repo: https://github.com/waze96/LSDS_Spark_Lab2

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
	
	
## Most retweeted APP
[!]	To obtain the copy-paste value to fill the array 'top20RtUsers' on the APP retweeted users 'out/topRtUsers': 
	
	[!] 	cat part-* | awk 'BEGIN {FS=","; OFS=" "}{print $NF,$1}' | tr -d '()' | sort -n -r | head -20 | awk '{print $2}' | sed 's/$/L, /' | tr -d '\n'

[!] To obtain the copy-paste value to fill the array 'tweetsOfTop20Users' on the APP retweeted users 'out/tweetsOfTop20Users': 
	
	[!]	cat part-* | awk 'BEGIN {FS=","; OFS=" "}{print $NF,$1}' | tr -d '()' | sort -n -r | head -200 | awk '{print $2}' | sed 's/$/L, /' | tr -d '\n'

[!]	In the output folder: outputDir/originalTweetsID

	[+] To obtain the list of Original Tweets of Most RT users: 
		[*] cat * | sed 's/$/L, /' | tr -d '\n'

[!]	In the output folder: outputDir/originalTweets_top20Users

	[+] To obtain the list of #RT, Original Tweet ID, UserID of Top20 RT users: 
		[*] cat * | sort -n | uniq -c | sort -n -r

	[+] To obtain the list of #RT, Original Tweet ID, UserID of Top20 RT users (Ordered by UserID, to identify tweets of the same user): 
		[*] cat * | sort -n | uniq -c | sort -n -k3


# TOP RT TWEETS

	1# Retweeted User
	User ID:			3143260474
	Tweet ID:			995356756770467840
	Tweet:				Ella está al mando. Con @PaquitaSalas nada malo puede pasar, ¿no? #Eurovision https://t.co/5HeUDCqxX6
	#RT: 				10809

	2# Retweeted User
	User ID:			24679473
	Tweet ID:			995372911920893953
	Tweet:				Twenty minutes to gooo… #allaboard #eurovision https://t.co/brQoCRmrXI
	#RT: 				393

	3# Retweeted User
	User ID:			15584187
	Tweet ID:			995378197477736448
	Tweet:				IT'S HERE! IT'S FINALLY HERE!🤩🤩#ESC2018 #AllAboard https://t.co/H4QjOio4a6
	#RT: 				397

	4# Retweeted User
	User ID:			39538010
	Tweet ID:			995383476181401601
	Tweet:				Already hate it 0/10 #Eurovision #esp
	#RT: 				933

	5# Retweeted User
	User ID:			38381308
	Tweet ID:			995420352657461248
	Tweet:				El dato: han desalojado a tres heterosexuales que se habían colado entre el público. #Eurovision #Eurocancion #YolaidayPacho12points
	#RT: 				528

	6# Retweeted User
	User ID:			739812492310896640
	Tweet ID:			995408052886147079
	Tweet:				Irlanda: Un puente, dos chiquitos que se quieren. #Eurovision https://t.co/B7ERrWdJdc
	#RT: 				141

	7# Retweeted User
	User ID:			1501434991
	Tweet ID:			995372692902699009
	Tweet:				Venga va, para no perder la tradición hoy habrá que tuitear un poquillo sobre Eurovision si o que mis panas.
	#RT: 				581

	8# Retweeted User
	User ID:			2754746065
	Tweet ID:			995371907301208064
	Tweet:				Pueden estar totalmente tranquilos Amaia y Alfred porque diría que España es el único país de todo Eurovision que c… https://t.co/75G6QPejYj
	#RT: 				518

	9# Retweeted User
	User ID:			3260160764
	Tweet ID:			995388112833531905
	Tweet:				Cuando te baja la regla y se te ha olvidado el támpax #Eurovision https://t.co/1agMCdjbCo
	#RT: 				144

	10# Retweeted User
	User ID:			93618138
	Tweet ID:			995333676614475776
	Tweet:				¡Ha ocurrido! @Amaia_ot2017 y @Alfred_ot2017 han conocido a @SalSobralMusic 👏👏👏 Han charlado un ratito con él en su… https://t.co/F4vrhj2qmU
	#RT: 				843


