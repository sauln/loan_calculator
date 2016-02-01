from django.test import LiveServerTestCase
from selenium import webdriver
from selenium.webdriver.common.keys import Keys


class NewVisitorTest(LiveServerTestCase):
	def setUp(self):
		self.browser = webdriver.Firefox()
	
	def tearDown(self):
		self.browser.quit()

	def test_user_goes_loan_calculator_page(self):
		#see we are going to the Loan Calculator site
		self.browser.get(self.live_server_url+"/loancalc")
		header_text = self.browser.find_element_by_tag_name('h1').text
		self.assertIn("Loan Calculator", header_text)

		#see a place to input a new loan		
		inputbox_bal = self.browser.find_element_by_id('id_new_balance')
		self.assertEqual(
				inputbox_bal.get_attribute('placeholder'),
				'Enter the balance'
		)

		inputbox_ir  = self.browser.find_element_by_id('id_interest_rate')
		self.assertEqual(
				inputbox_ir.get_attribute('placeholder'),
				'Enter the interest rate'
		)
		inputbox_min = self.browser.find_element_by_id('id_min_payment')
		self.assertEqual(
				inputbox_min.get_attribute('placeholder'),
				'Enter the minimum payment'
		)
		
		
		
		
		#adds item and is redirected to a new page that shows the list
		#inputbox.send_keys('Buy peacock feathers')
		#inputbox.send_keys(Keys.ENTER)
		#edith_list_url = self.browser.current_url
		#self.assertRegex(edith_list_url, '/lists/.+')
		#self.check_for_row_in_list_table('1: Buy peacock feathers')

