package excel;


public class Constants {

	public static final String ong_sowcfg_std_new_material_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_new_material_lov("
            + "id, source, product, model, document, change, engine, module," 
            + "module_name, pagina, sow_index, part_number, nomenclature, status," 
            + "bom_qty, display_in_sow_material_section, display_in_sow_material_as," 
            + "price_category, new_repair, service_rendered, fall_out) VALUES(";
	
	public static final String ong_sowcfg_std_module_operations_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_module_operations_list_lov("
            + "id, plc_product_type, plc_code_routing, engine, module_code,num_operation, operation_name,operation_type,std_operation)" 
            + " VALUES(";
	
	public static final String ong_sowcfg_std_engine_operations_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_engine_operations_list_lov("
            + "id, plc_product_type, plc_code_routing, engine, num_operation,operation_name,operation_type,std_operation)" 
            + " VALUES(";
	
	public static final String ong_sowcfg_std_engine_modules_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_engine_module_lov("
            + "id, plc_product_type, plc_module, engine, module_code, module_name)" 
            + " VALUES(";
	
	public static final String ong_sowcfg_std_shop_details_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_shop_details_lov("
            + "id, engine_model, plc_product_type, shop_name, shop_labor_hour_unit_price,labor_hour_unit_cost)" 
            + " VALUES(";
	
	public static final String ong_sowcfg_std_repair_supplier_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_repair_supplier_lov("
            + "id,price_list_type,supplier_type,repair_supplier)" 
            + " VALUES(";
	
	public static final String role_details = "INSERT INTO role_details(table_id, user_name, user_email, user_sso, role_id, created_by,"
			+ "created_date, updated_by, updated_date) VALUES(";
	
	public static final String master_data = "INSERT INTO master_data(constant_id, constant_type, constant_value, created_by,"
			+ "created_date, updated_by, updated_date) VALUES(";
	
	public static final String ong_sowcfg_std_service_rendered_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_service_rendered_lov("
            + "id, service_rendered, unit_price)" 
            + " VALUES(";
	
	public static final String ong_sowcfg_std_sb_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_sb_lov("
            + "std_sb_id,sb_id, sb_title,sow_sb_description)" 
            + " VALUES(";
	
	public static final String ong_sowcfg_std_sb_rules_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_sb_rules_lov("
            + "std_sb_rules_id,std_sb_id,sow_sb_description,sow_name,std_sb_rule_color)" 
            + " VALUES(";
	
	public static final String ong_sowcfg_std_engine_operation_hrs_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_engine_operation_hrs("
            + "engine_operation_hrs_id, engine_operations_id, plc_product_type,engine,operation_name,model,operation_hrs)" 
            + " VALUES(";
	
	public static final String ong_sowcfg_std_module_operation_hrs_lov = "INSERT INTO repairs_usm_portal.ong_sowcfg_std_module_operation_hrs("
            + "module_operation_hrs_id, module_operations_id, plc_product_type,engine,operation_name,module,model,operation_hrs)" 
            + " VALUES(";
}
