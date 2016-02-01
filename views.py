from django.shortcuts import render, redirect
from django.http import HttpResponse

from loan_calculator.models import Loan

def loancalc_page(request):
	if request.method == 'POST':
		balance=request.POST["balance"]
		interest_rate=request.POST["interest_rate"]
		minimum_payment=request.POST["minimum_payment"]
		print("Balance: %s\nInterest rate: %s\n Minimum payment: %s"%(balance, interest_rate, minimum_payment))
		
		chk = Loan(balance=balance, interest_rate=interest_rate, minimum_payment=minimum_payment)
		chk.save()	
		#print(loans)
		loans = Loan.objects.all()
		print(loans)
		
		return redirect('/loancalc/', {'loans': loans})

	loans = Loan.objects.all()

	return render(request, 'loancalc.html', {'loans': loans})
	
