#
#
# Aufgabe 1.5
# Teilaufgabe a)
data(mtcars)
#
# Teilaufgabe b) 
attach(mtcars)
# 4 Plots in ein Fenster
par(mfrow=c(2,2))
hist(mpg)
hist(cyl)
hist(hp,breaks=c(0,100,200,300,400))
hist(wt)
#
# Teilaufgabe c) 
# Kuchendiagramm und Stab-/Balkendiagramm nur sinnvoll für wenige Merkmalsausprägungen wie bei cyl
#
plot(table(hp))
#
# Teilaufgabe d) 
# absolute frequency table of number of cylinders
table(cyl)
barplot(table(cyl))
pie(table(cyl),labels=c(4,6,8),col=rainbow(3),radius=0.9)
#
# relative frequency table of number of cylinders (in percent)
table(cyl)/length(cyl)*100
#
# Teilaufgabe e) 
par(mfrow=c(1,2))
plot(hp,mpg)
cor(mpg,hp)
plot(wt,mpg)
cor(mpg,wt)

