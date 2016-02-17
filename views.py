from django.shortcuts import render, redirect
from django.http import HttpResponse

from loan_calculator.models import Loan, Portfolio


class SummaryStats():
	def __init__(self, loans):
		self.total_debt = sum(loan.balance for loan in loans)

def portfolio_page(request):
	loans = list(Loan.objects.all())
	summary = SummaryStats(loans) 
	return render(request, 'portfolio.html', {'loans': loans, 'summary':summary})

def new_portfolio(request):
	balance=request.POST["balance"]
	interest_rate=request.POST["interest_rate"]
	minimum_payment=request.POST["minimum_payment"]
	
	portfolio_ = Portfolio.objects.create()

	chk = Loan(balance=balance, 
				interest_rate=interest_rate, 
				minimum_payment=minimum_payment,
				portfolio = portfolio_)
	chk.save()	
	
	return redirect('/portfolio/the-only-portfolio/')


def loancalc_page(request):
	#if request.method == 'POST':
	#	balance=request.POST["balance"]
	#	interest_rate=request.POST["interest_rate"]
	#	minimum_payment=request.POST["minimum_payment"]
	#	chk = Loan(balance=balance, 
	#				interest_rate=interest_rate, 
	#				minimum_payment=minimum_payment)
	#	chk.save()	
	#	
	#	return redirect('/portfolio/the-only-portfolio/')


	#loans = list(Loan.objects.all())
	#summary = SummaryStats(loans) 
	return render(request, 'loancalc.html') #, {'loans': loans, 'summary':summary})

	#loans = Loan.objects.all()
	#return render(request, 'loancalc.html', {'loans': loans})
	
