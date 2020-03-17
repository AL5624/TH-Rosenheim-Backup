# Plots der Wahrscheinlichkeitsfunktion bzw. -dichte für
# verschiedene Verteilungen
#
# Binomialverteilung
par(mfrow=c(3,1))
x<-c(0:10)
plot(x, dbinom(x,10,0.5), main="B_(10,0.5)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.3))
plot(x, dbinom(x,10,0.3), main="B_(10,0.3)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.3))
plot(x, dbinom(x,10,0.6), main="B_(10,0.6)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.3))
#
# Poissonverteilung
par(mfrow=c(2,2))
x<-c(0:10)
plot(x, dpois(x,1), main="P_1", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.5))
plot(x, dpois(x,2), main="P_2", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.5))
plot(x, dpois(x,3), main="P_3", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.5))
plot(x, dbinom(x,100,0.03), main="B_(100,0.03)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.5))
#
# Hypergeometrische Verteilung
par(mfrow=c(2,2))
x<-c(0:10)
plot(x, dhyper(x,10,10,10), main="H_(10,10,10)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.5))
plot(x, dhyper(x,20,20,10), main="H_(20,20,10)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.5))
plot(x, dhyper(x,30,30,10), main="H_(30,30,10)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.5))
plot(x, dbinom(x,10,0.5), main="B_(10,0.5)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, xlim=c(0,10), ylim=c(0,0.5))
#
# Exponentialverteilung
par(mfrow=c(3,1))
curve(dexp(x,1),from=0,to=10, main="Exp_(1)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,1))
curve(dexp(x,2),from=0,to=10, main="Exp_(2)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,1))
curve(dexp(x,0.5),from=0,to=10, main="Exp_(0.5)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,1))
#
# Chi-Quadrat-Verteilung
par(mfrow=c(3,1))
curve(dchisq(x,1),from=0,to=10, main="Chisq_1", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,0.5))
curve(dchisq(x,2),from=0,to=10, main="Chisq_2", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,0.5))
curve(dchisq(x,3),from=0,to=10, main="Chisq_3", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,0.5))
#
# t-Verteilung
par(mfrow=c(1,1))
curve(dt(x,1),from=-3,to=3, main="t_1, t_2, t_10, n_(0,1)", cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,0.4), col="green")
curve(dt(x,2),from=-3,to=3,  cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,0.4), add=TRUE, col="orange")
curve(dt(x,10),from=-3,to=3,  cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,0.4), add=TRUE, col="red")
curve(dnorm(x),from=-3,to=3, cex.axis=1.5, cex.lab=1.5, 
     cex.main=1.5, lwd=2, ylim=c(0,0.4), add=TRUE, col="blue")







