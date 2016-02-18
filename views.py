from django.shortcuts import render, redirect
from django.http import HttpResponse

from loan_calculator.models import Loan, Portfolio


class SummaryStats():
	def __init__(self, loans):
		self.total_debt = sum(loan.balance for loan in loans)


def portfolio_page(request, port_id):
	loans = list(Loan.objects.all())
	portfolio_ = Portfolio.objects.get(id=port_id)
	summary = SummaryStats(portfolio_.loan_set.all())
	print(summary.total_debt)
	return render(request, 'portfolio.html', {'portfolio': portfolio_})

def add_loan(request, portfolio_id):

	balance=request.POST["balance"]
	interest_rate=request.POST["interest_rate"]
	minimum_payment=request.POST["minimum_payment"]
	
	portfolio_ = Portfolio.objects.get(id=portfolio_id)

	chk = Loan(balance=balance, 
				interest_rate=interest_rate, 
				minimum_payment=minimum_payment,
				portfolio = portfolio_)
	chk.save()	

	return redirect('/portfolio/%s/'%(portfolio_.id,))

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
	
	return redirect('/portfolio/%s/'%(portfolio_.id,))


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
	
