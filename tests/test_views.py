from django.test import TestCase
from django.core.urlresolvers import resolve
from django.http import HttpRequest

from loan_calculator.views import loancalc_page
from loan_calculator.models import Loan

class LoanCalcTest(TestCase):
	def test_loancalc_url_resolves_to_loancalc_view(self):
		found = resolve('/loancalc/')
		self.assertEqual(found.func, loancalc_page)

	def test_saving_and_retrieving_items(self):
		first_item = Loan()
		first_item.balance = 123.321
		first_item.minimum_payment = 5
		first_item.interest_rate = 2.4
		first_item.save()

		saved_items = Loan.objects.all()
		self.assertEqual(saved_items.count(), 1)

		first_saved_item = saved_items[0]
		self.assertEqual(first_saved_item.balance, 123.321)
		self.assertEqual(first_saved_item.minimum_payment, 5)
		self.assertEqual(first_saved_item.interest_rate, 2.4)

	#def test_home_page_can_save_a_POST_request(self):
	#	request = HttpRequest()
	#	request.method = 'POST'
	#	request.POST['item_text'] = 'A new list item'

	#	response = home_page(request)

	#	self.assertEqual(Item.objects.count(), 1)
	#	new_item = Item.objects.first()
	#   self.assertEqual(new_item.text, 'A new list item')


	def test_home_page_returns_correct_html(self):
		request = HttpRequest()
		response = loancalc_page(request)

		self.assertTrue(response.content.strip().startswith(b'<!DOCTYPE html>'),
			"Actual response = %s"%response.content.strip())
		self.assertIn(b'The Loan Calculator', response.content)
		self.assertTrue(response.content.strip().endswith(b'</html>'), 
			"Actual response = %s"%response.content)




