x<- 0.1+0.1+0.1
# Absoluter Fehler
abs<-x-0.3
# Relativer Fehler
rel<-abs/0.3

# Double precision: eps = 2^(-52)
eps<- 2^(-52)
# Maschinengenauigkeit
.Machine$double.eps

# Test auf Gleichheit
x == 0.3

# Test, ob 2 Zahlen fast gleich sind
all.equal(x,0.3)


sum1 = 0
for (k in 1:10^6){
  sum1<- signif(sum1 + 1/k,6)
}
sum1

idx <- seq(from = 10^6, to = 1, by = -1)
sum2 = 0
for (k in idx){
  sum2 = signif(sum2 + 1/k,6)
}
sum2

sum = 0
for (k in 1:10^6){
  sum<- sum + 1/k
}
sum




