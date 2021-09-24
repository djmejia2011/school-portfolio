# this simple calculator is used to calculate how much money i will have after
# each week by investing with average return of 1.2% per week plus additional 150 every week.

start =3655                                                     #initial investment 
RATE = .012                                                     #average return per week
additionalCash = 150                                            #additional cash every week
totalcash = 3655                     
trade = 360                          


for i in range(trade):
	AdditionalCash = (start*RATE)                                 #cash eaned per week
	#every week cash eaned and additional weekly cash is included for next week investment
  start = start+(start*RATE)+additionalCash
	totalcash += additionalCash                                   #keeps track how much cash i have invested
	print("Week",i+1,":","$"+str(round(start,2)),                 #displays cash at the end of the week
	"      \tcash: ","$"+str(round(totalcash,2)),                 #displays total cash at begining of the week
	"\t+"+str(RATE*100)+"% = ","$"+str(round(AdditionalCash,2)))  #money earned that week.
	i += 1
