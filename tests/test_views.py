from django.test import TestCase
from django.core.urlresolvers import resolve
from django.http import HttpRequest

from loan_calculator.views import loancalc_page, SummaryStats, add_loan, new_portfolio
from loan_calculator.models import Loan, Portfolio

class NewPortfolioTest(TestCase):
	def test_redirects_after_a_POST_request(self):
		Loan.objects.all().delete()

		request = HttpRequest()
		request.method = 'POST'
		request.POST["balance"] = 12345
		request.POST["interest_rate"] = 0.5
		request.POST["minimum_payment"] = 11
		response = new_portfolio(request)
		self.assertEqual(response['location'], '/portfolio/1/')
		self.assertTemplateUsed(response, 'portfolio.html')

	def test_home_page_can_save_new_POST_request(self):
		Loan.objects.all().delete()

		request = HttpRequest()
		request.method = 'POST'
		request.POST["balance"] = 12345
		request.POST["interest_rate"] = 0.5
		request.POST["minimum_payment"] = 11
		response = new_portfolio(request)
		
		self.assertEqual(Loan.objects.count(), 1)
		new_item = Loan.objects.first()

		self.assertEqual(new_item.balance, 12345)
		self.assertEqual(new_item.interest_rate, 0.5)
		self.assertEqual(new_item.minimum_payment, 11)


class LoanCalcTest(TestCase):
	def test_loancalc_url_resolves_to_loancalc_view(self):
		found = resolve('/loancalc/')
		self.assertEqual(found.func, loancalc_page)
		

	def setUp(self):
		self.port = Portfolio()	
		self.first_item = Loan()
		self.first_item.balance = 123.321
		self.first_item.minimum_payment = 5
		self.first_item.interest_rate = 2.4
		self.first_item.portfolio = self.port
		self.first_item.save()
		self.second_item = Loan()
		self.second_item.balance = 2345.0
		self.second_item.minimum_payment = 50
		self.second_item.interest_rate = 11.1
		self.second_item.portfolio = self.port
		self.second_item.save()

	def tearDown(self):
		Loan.objects.all().delete()

	def test_finds_sum_of_loans(self):
		saved_items = Loan.objects.all()
		summary = SummaryStats(saved_items)
		self.assertEqual(summary.total_debt, 123.321 + 2345.0)

	def test_saving_and_retrieving_items(self):
		saved_items = Loan.objects.all()
		self.assertEqual(saved_items.count(), 2)

		first_saved_item = saved_items[0]
		self.assertEqual(first_saved_item.balance, 123.321)
		self.assertEqual(first_saved_item.minimum_payment, 5)
		self.assertEqual(first_saved_item.interest_rate, 2.4)
	

	def test_home_page_returns_correct_html(self):
		request = HttpRequest()
		response = loancalc_page(request)

		self.assertTrue(response.content.strip().startswith(b'<!DOCTYPE html>'),
			"Actual response = %s"%response.content.strip())
		self.assertIn(b'The Loan Calculator', response.content)
		self.assertTrue(response.content.strip().endswith(b'</html>'), 
			"Actual response = %s"%response.content)




