from django.shortcuts import render, redirect
from django.http import HttpResponse

from loan_calculator.models import Loan

def loancalc_page(request):
	if request.method == 'POST':
		Loan.objects.create(balance=request.POST["balance"], interest_rate=request.POST["interest_rate"], minimum_payment=request.POST["minimum_payment"])
		
		loans = Loan.objects.all()
		return redirect('/loancalc/add_loan', {'loans': loans})

	loans = Loan.objects.all()

	return render(request, 'loancalc.html', {'loans': loans})
	
