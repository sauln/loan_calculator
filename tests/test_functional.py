from django.test import LiveServerTestCase
from selenium import webdriver
from selenium.webdriver.common.keys import Keys


class NewVisitorTest(LiveServerTestCase):
	def setUp(self):
		self.browser = webdriver.Firefox()
	
	def tearDown(self):
		self.browser.quit()

	def check_for_row_in_list_table(self, row_text):
		table = self.browser.find_element_by_id('id_loan_table')
		rows = table.find_elements_by_tag_name('tr')
		self.assertIn(row_text, [row.text for row in rows])
	
	
	def test_user_goes_loan_calculator_page(self):
		#see we are going to the Loan Calculator site
		self.browser.get(self.live_server_url+"/loancalc")
		header_text = self.browser.find_element_by_tag_name('h1').text
		self.assertIn("Loan Calculator", header_text)

		#see a place to input a new loan		
		inputbox_bal = self.browser.find_element_by_id('id_balance')
		self.assertEqual(
				inputbox_bal.get_attribute('placeholder'),
				'Balance')
		inputbox_ir  = self.browser.find_element_by_id('id_interest_rate')
		self.assertEqual(
				inputbox_ir.get_attribute('placeholder'),
				'Interest Rate')
		inputbox_min = self.browser.find_element_by_id('id_minimum_payment')
		self.assertEqual(
				inputbox_min.get_attribute('placeholder'),
				'Minimum Payment')
		
		#adds item and is redirected to a new page that shows the list
		inputbox_bal.send_keys(12000)
		inputbox_ir.send_keys(5.6)
		inputbox_min.send_keys(100)

		self.browser.find_element_by_id("add").click()
		self.check_for_row_in_list_table('$12,000.00')

		edith_list_url = self.browser.current_url
		self.assertRegex(edith_list_url, '/loancalc/.+')

